package com.wigm.tr.service;

import com.wigm.tr.aspect.Tiempo;
import com.wigm.tr.entities.dto.ActualizarOrdenDTO;
import com.wigm.tr.entities.dto.CrearOrdenDTO;
import com.wigm.tr.entities.dto.OrdenDTO;
import com.wigm.tr.entities.dto.ProductDTO;
import com.wigm.tr.entities.exception.ExcepcionGenerica;
import com.wigm.tr.entities.repository.Orden;
import com.wigm.tr.entities.repository.Producto;
import com.wigm.tr.entities.repository.Sucursal;
import com.wigm.tr.repository.OrdenRepository;
import com.wigm.tr.repository.ProductoRepository;
import com.wigm.tr.repository.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenServiceImpl implements OrdenService{
    private OrdenRepository ordenRepository;
    private SucursalRepository sucursalRepository;
    private ProductoRepository productoRepository;

    public OrdenServiceImpl(OrdenRepository ordenRepository,
                            SucursalRepository sucursalRepository,
                            ProductoRepository productoRepository) {
        this.ordenRepository = ordenRepository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }

    @Tiempo
    @Override
    public List<OrdenDTO> obtenerOrdenes() {
        List<Orden> ordenList = ordenRepository.findAll();
        List<OrdenDTO> ordenDTO = new ArrayList<>();
        ordenList.forEach( orden -> {
            OrdenDTO ordeneDTO = new OrdenDTO();
            sucursalRepository.findById(orden.getSucursal().getId())
                    .ifPresent((or)-> ordeneDTO.setSucursal(or.getNombre()));
                List<ProductDTO> productos = new ArrayList<>();
            productoRepository.findByOrden(orden).stream().forEach(or ->{
                ProductDTO productDTO = new ProductDTO(or.getCodigo(), or.getDescripcion(), or.getPrecio());
                productos.add(productDTO);
            });
            BeanUtils.copyProperties(orden, ordeneDTO);
            ordeneDTO.setProductos(productos);
            ordenDTO.add(ordeneDTO);
        });
        return ordenDTO;
    }

    @Tiempo
    @Override
    public void crearNuevaOrden(CrearOrdenDTO crearOrdenDTO) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(crearOrdenDTO.sucursal());
        sucursalRepository.save(sucursal);

        Orden orden = new Orden();
        orden.setSucursal(sucursal);
        orden.setFecha(LocalDate.now());

        List<Producto> productos = new ArrayList<>();

        BigDecimal total = crearOrdenDTO.productos().stream()
                .map(productDTO -> productDTO.precio())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        crearOrdenDTO.productos().forEach(
                productDTO -> {
                    Producto productDTO1 = new Producto();
                    BeanUtils.copyProperties(productDTO, productDTO1);
                    productos.add(productDTO1);
                }
        );
        orden.setTotal(total);
        ordenRepository.save(orden);

        productos.forEach(producto -> {
            producto.setOrden(orden);
            productoRepository.save(producto);
        });

    }

    @Tiempo
    @Transactional
    @Override
    public void actualizarOrden(ActualizarOrdenDTO actualizarOrdenDTO) {
        Orden orden = ordenRepository.findById(actualizarOrdenDTO.ordenId())
                .orElseThrow(()-> new ExcepcionGenerica("Orden No encontrada"));
        sucursalRepository.findById(orden.getSucursal().getId())
                .ifPresent(or -> or.setNombre(actualizarOrdenDTO.sucursal()));
        productoRepository.deleteByOrdenId(orden.getId());
        List<Producto> nuevosPorductos = new ArrayList<>();
        actualizarOrdenDTO.productos().forEach( o->{
            Producto pr = new Producto();
            pr.setOrden(orden);
            pr.setCodigo(o.codigo());
            pr.setDescripcion(o.descripcion());
            pr.setPrecio(o.precio());
            nuevosPorductos.add(pr);
        });
        productoRepository.saveAll(nuevosPorductos);
        orden.setTotal(nuevosPorductos.stream()
                .map(pro -> pro.getPrecio())
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        ordenRepository.save(orden);
    }
}

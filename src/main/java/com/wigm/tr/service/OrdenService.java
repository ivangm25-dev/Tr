package com.wigm.tr.service;

import com.wigm.tr.entities.dto.ActualizarOrdenDTO;
import com.wigm.tr.entities.dto.CrearOrdenDTO;
import com.wigm.tr.entities.dto.OrdenDTO;

import java.util.List;

public interface OrdenService {
    List<OrdenDTO> obtenerOrdenes();
    void crearNuevaOrden(CrearOrdenDTO crearOrdenDTO);
    void actualizarOrden(ActualizarOrdenDTO actualizarOrdenDTO);
}

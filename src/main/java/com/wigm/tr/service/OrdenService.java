package com.wigm.tr.service;

import com.wigm.tr.entities.dto.ActualizarOrdenDTO;
import com.wigm.tr.entities.dto.CrearOrdenDTO;
import com.wigm.tr.entities.dto.OrdenDTO;

import java.util.List;

public interface OrdenService {
    /**
     * Regresa todas las ordenes.
     * @return List<OrdenDTO>
     */
    List<OrdenDTO> obtenerOrdenes();

    /**
     * Crea nueva orden
     * @param crearOrdenDTO
     */
    void crearNuevaOrden(CrearOrdenDTO crearOrdenDTO);

    /**
     * Actualiza nueva orden.
     * @param actualizarOrdenDTO
     */
    void actualizarOrden(ActualizarOrdenDTO actualizarOrdenDTO);
}

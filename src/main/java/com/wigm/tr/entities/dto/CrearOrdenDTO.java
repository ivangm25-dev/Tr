package com.wigm.tr.entities.dto;

import java.util.List;

public record CrearOrdenDTO(
        String sucursal,
        List<ProductDTO> productos) {
}

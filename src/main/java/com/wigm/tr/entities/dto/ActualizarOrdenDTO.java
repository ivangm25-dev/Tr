package com.wigm.tr.entities.dto;

import java.util.List;

public record ActualizarOrdenDTO(Long ordenId, String sucursal, List<ProductDTO> productos) {
}

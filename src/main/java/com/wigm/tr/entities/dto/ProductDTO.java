package com.wigm.tr.entities.dto;

import java.math.BigDecimal;

public record ProductDTO(String codigo,
                         String descripcion,
                         BigDecimal precio) {
}

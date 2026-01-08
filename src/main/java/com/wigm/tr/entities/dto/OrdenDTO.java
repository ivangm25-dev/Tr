package com.wigm.tr.entities.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrdenDTO {
    private Long id;
    private String sucursal;
    private LocalDate fecha;
    private BigDecimal total;
    private List<ProductDTO> productos;
}

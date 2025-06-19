package com.dashboard.dati.registerDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dashboard.dati.model.enums.InvoiceType;

import lombok.Data;

@Data
public class InvoiceDTO {
    private String description;
    private BigDecimal amount;
    private BigDecimal ivaPercentage;
    private LocalDate date;
    private InvoiceType type;
}

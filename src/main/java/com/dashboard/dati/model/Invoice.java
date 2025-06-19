package com.dashboard.dati.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dashboard.dati.model.enums.InvoiceType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal amount;

    private BigDecimal ivaPercentage; // ad esempio 0.22 per 22%

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private InvoiceType type; // INCOME o EXPENSE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    // Metodo per calcolare l'IVA in valore assoluto
    public BigDecimal getIvaAmount() {
        if (amount == null || ivaPercentage == null)
            return BigDecimal.ZERO;
        return amount.multiply(ivaPercentage);
    }

    // Metodo per calcolare l'importo totale con IVA
    public BigDecimal getTotalAmount() {
        if (amount == null || ivaPercentage == null)
            return BigDecimal.ZERO;
        return amount.add(getIvaAmount());
    }
}

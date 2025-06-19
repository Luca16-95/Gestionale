package com.dashboard.dati.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dashboard.dati.model.Invoice;
import com.dashboard.dati.model.User;
import com.dashboard.dati.registerDto.InvoiceDTO;
import com.dashboard.dati.repository.InvoiceRepository;
import com.dashboard.dati.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;

    @Transactional
    public Invoice createInvoice(InvoiceDTO dto, String username) {
        User user = userRepository.findByUsername(username);

        Invoice invoice = Invoice.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .ivaPercentage(dto.getIvaPercentage())
                .date(dto.getDate())
                .type(dto.getType())
                .createdBy(user)
                .build();

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

}

package com.dashboard.dati.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dashboard.dati.model.Invoice;
import com.dashboard.dati.registerDto.InvoiceDTO;
import com.dashboard.dati.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String createInvoice(@ModelAttribute InvoiceDTO dto, Principal principal, Model model) {
        Invoice saved = invoiceService.createInvoice(dto, principal.getName());
        model.addAttribute("invoice", saved);
        return "redirect:/invoice/invoices";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String showInvoiceForm(Model model) {
        model.addAttribute("invoice", new InvoiceDTO());
        return "invoice-form";
    }

    @GetMapping("/invoices")
    @PreAuthorize("hasRole('ADMIN', 'MANAGER', 'EMPLOYEE')")
    public String listInvoices(Model model) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("invoices", invoices);
        return "invoice-list";
    }
}

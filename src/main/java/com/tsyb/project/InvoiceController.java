package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController()
    {
        //default
    }

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/findallinvoices")
    public String findAll(Model model) {
        model.addAttribute("invoiceslist", invoiceService.findAll());
        return "invoiceslist";
    }

    @GetMapping("/invoices/{id}")
    public String getInvoice(Model model, @PathVariable int id) {

        Invoice invoice = invoiceService.findById(id);

        if (invoice == null) {
            throw new RuntimeException("Invoice id not found - " + id);
        }

        model.addAttribute("invoiceslist", invoice);
        return "invoiceslist";
    }

    @GetMapping("/addinvoicesform")
    public String addInvoicesForm(Model model) {
        Invoice invoice = new Invoice();

        model.addAttribute("invoice", invoice);

        return "addinvoicesform";
    }

    @PostMapping("/addinvoices")
    public String addInvoice(@ModelAttribute("invoice") Invoice invoice) {
        invoice.setId(0);
        invoiceService.save(invoice);
        return "";
    }

    @GetMapping("/updateinvoicesform/{id}")
    public String updateInvoicesForm(@PathVariable("id") int id, Model model) {
        Invoice invoice = invoiceService.findById(id);
        if (invoice == null) {
            throw new RuntimeException("Invoice id not found - " + id);
        }

        model.addAttribute("invoice", invoice);
        return "updateinvoicesform";
    }

    @PostMapping("/updateinvoices")
    public String updateInvoice(Model model, @ModelAttribute("invoice") Invoice invoice) {

        invoiceService.save(invoice);
        model.addAttribute("invoiceslist", invoiceService.findAll());

        return "";
    }


    @PostMapping("/deleteinvoices/{id}")
    public String deleteInvoice(Model model, @PathVariable int id) {

        Invoice tempInvoice = invoiceService.findById(id);

        if (tempInvoice == null) {
            throw new RuntimeException("Invoice id not found - " + id);
        }

        invoiceService.deleteById(id);
        model.addAttribute("invoiceslist", invoiceService.findAll());
        return "invoiceslist";
    }
}
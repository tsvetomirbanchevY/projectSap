package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(path = "/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    GenericWebApplicationContext context;

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

    @GetMapping("/findalldailyinvoices")
    public String findAllDaily(Model model) {
        Users user = (Users)context.getBean("usertemp");
        model.addAttribute("invoiceslist", invoiceService.findAllDaily(user.getId()));
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
        return "home";
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

    @PostMapping("/updateinvoices/{id}")
    public String updateInvoice(Model model, @PathVariable("id") int id, @ModelAttribute("invoice") Invoice invoice) {

        invoiceService.update(invoice);
        model.addAttribute("invoiceslist", invoiceService.findAll());

        return "invoiceslist";
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

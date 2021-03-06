package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.List;

@Controller
@RequestMapping(path = "/voucher")
public class VoucherController {

    private VoucherService voucherService;

    @Autowired
    GenericWebApplicationContext context;

    public VoucherController() {
        //default
    }

    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/findallvouchers")
    public String findAll(Model model) {
        model.addAttribute("voucherlist", voucherService.findAll());

        return "voucherlist";
    }

    @GetMapping("/findallmonthlyinvoices")
    public String findAllMonthly(Model model) {
        Users user = (Users) context.getBean("usertemp");
        model.addAttribute("invoiceslist", voucherService.findAllMyVouchers(user.getId()));
        return "invoiceslist";
    }
    @GetMapping("/voucher/{id}")
    public String getVouchers(Model model, @PathVariable int id) {

        Voucher voucher = voucherService.findById(id);

        if (voucher == null) {
            throw new RuntimeException("Voucher id not found - " + id);
        }
        model.addAttribute("voucherlist", voucherService.findAll());

        return "voucherlist";
    }


    @GetMapping("/addvouchersform")
    public String addVouchersForm(Model model) {
        Voucher voucher = new Voucher();

        model.addAttribute("voucher", voucher);

        return "addvouchersform";
    }

    @PostMapping("/addvouchers")
    public String addVoucher(@ModelAttribute("voucher") Voucher voucher) {
        voucher.setId(0);
        voucherService.save(voucher);
        return "";
    }

    @GetMapping("/updatevouchersform/{id}")
    public String updateVouchersForm(@PathVariable("id") int id, Model model) {
        Voucher voucher = voucherService.findById(id);
        if (voucher == null) {
            throw new RuntimeException("Voucher id not found - " + id);
        }

        model.addAttribute("voucher", voucher);
        return "updatevouchersform";
    }

    @PostMapping("/updatevouchers/{id}")
    public String updateVoucher(Model model, @PathVariable("id") int id, @ModelAttribute("voucher") Voucher voucher) {

        voucherService.update(voucher);
        model.addAttribute("voucherslist", voucherService.findAll());

        return "";
    }


    @PostMapping("/deletevouchers/{id}")
    public String deleteVoucher(Model model, @PathVariable int id) {

        Voucher tempVoucher = voucherService.findById(id);

        if (tempVoucher == null) {
            throw new RuntimeException("Voucher id not found - " + id);
        }

        voucherService.deleteById(id);
        model.addAttribute("voucherslist", voucherService.findAll());
        return "voucherslist";
    }
}
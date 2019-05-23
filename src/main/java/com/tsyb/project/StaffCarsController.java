package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.List;


@Controller
@RequestMapping(path = "/staffcars")
public class StaffCarsController {

    private StaffCarsService staffCarsService;


    public StaffCarsController()
    {
        //default
    }

    @Autowired
    public StaffCarsController(StaffCarsService staffCarsService) {
        this.staffCarsService = staffCarsService;
    }


    @GetMapping("/findallstaffcars")
    public String findAll(Model model) {
        model.addAttribute("staffcarslist", staffCarsService.findAll());
        return "staffcarslist";
    }

    @GetMapping("/staffcars/{id}")
    public String getStaffCar(Model model, @PathVariable int id) {

        StaffCars staffCar = staffCarsService.findById(id);

        if (staffCar == null) {
            throw new RuntimeException("StaffCar id not found - " + id);
        }

        model.addAttribute("staffcarslist", staffCar);
        return "staffcarslist";
    }

    @GetMapping("/addstaffcarsform")
    public String addStaffCarsForm(Model model) {
        StaffCars staffCar = new StaffCars();

        model.addAttribute("staffcars", staffCar);

        return "addstaffcarsform";
    }

    @PostMapping("/addstaffcars")
    public String addStaffCar(@ModelAttribute("staffcars") StaffCars staffCar) {
        staffCar.setId(0);
        staffCarsService.save(staffCar);
        return "staffcarslist";
    }

    @GetMapping("/updatestaffcarsform/{id}")
    public String updateStaffCarsForm(@PathVariable("id") int id, Model model) {
        StaffCars staffCar = staffCarsService.findById(id);
        if (staffCar == null) {
            throw new RuntimeException("StaffCar id not found - " + id);
        }

        model.addAttribute("staffcars", staffCar);
        return "updatestaffcarsform";
    }

    @PostMapping("/updatestaffcars/{id}")
    public String updateStaffCar(Model model, @PathVariable("id") int id, @ModelAttribute("staffcars") StaffCars staffCar) {

        staffCarsService.update(staffCar);
        model.addAttribute("staffcarslist", staffCarsService.findAll());

        return "staffcarslist";
    }


    @PostMapping("/deletestaffcars/{id}")
    public String deleteStaffCar(Model model, @PathVariable int id) {

        StaffCars tempStaffCar = staffCarsService.findById(id);

        if (tempStaffCar == null) {
            throw new RuntimeException("StaffCar id not found - " + id);
        }

        staffCarsService.deleteById(id);
        model.addAttribute("staffcarslist", staffCarsService.findAll());
        return "staffcarslist";
    }
}

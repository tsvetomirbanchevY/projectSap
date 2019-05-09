package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/staffCars")
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
        model.addAttribute("cstaffarslist", staffCarsService.findAll());
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
        return "";
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

    @PostMapping("/updatestaffcars")
    public String updateStaffCar(Model model, @ModelAttribute("staffcars") StaffCars staffCar) {

        staffCarsService.save(staffCar);
        model.addAttribute("staffcarslist", staffCarsService.findAll());

        return "";
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

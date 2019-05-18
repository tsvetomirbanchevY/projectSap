package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class CarsController {

    private CarsService carsService;

    @Autowired
    private GenericWebApplicationContext context;


    public CarsController() {
        //default
    }

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/findallavailablecars")
    public String findAllAvailable(Model model) {
        Users user = (Users)context.getBean("usertemp");
        System.out.println(user.getUserName());

        model.addAttribute("carslist", carsService.findByIsAvailable(user.getCity()));
        return "carslist";
    }

    @PostMapping("/endtrip")
    public String endTrip(Model model) {
        Cars currentCar = (Cars)context.getBean("cartemp");
        if(currentCar.getBattery()!=0){
            currentCar.setAvailable(true);
        }
        carsService.save(currentCar);
        return "/home";
    }


    @GetMapping("/findallcars")
    public String findAll(Model model) {
        model.addAttribute("carslist", carsService.findAll());
        return "carslist";
    }

    @GetMapping("/cars/{id}")
    public String getCar(Model model, @PathVariable int id) {

        Cars car = carsService.findById(id);

        if (car == null) {
            throw new RuntimeException("Car id not found - " + id);
        }

        model.addAttribute("carslist", car);
        return "carslist";
    }

    @GetMapping("/addcarsform")
    public String addCarsForm(Model model) {
        Cars car = new Cars();

        model.addAttribute("cars", car);

        return "addcarsform";
    }

    @PostMapping("/addcars")
    public String addCar(@ModelAttribute("cars") Cars car) {
        car.setId(0);
        carsService.save(car);
        return "";
    }

    @GetMapping("/updatecarsform/{id}")
    public String updateCarsForm(@PathVariable("id") int id, Model model) {
        Cars car = carsService.findById(id);
        if (car == null) {
            throw new RuntimeException("Car id not found - " + id);
        }

        model.addAttribute("cars", car);
        return "updatecarsform";
    }

    @PostMapping("/updatecars")
    public String updateCar(Model model, @ModelAttribute("cars") Cars car) {

        carsService.save(car);
        model.addAttribute("carslist", carsService.findAll());

        return "";
    }


    @PostMapping("/deletecars/{id}")
    public String deleteCar(Model model, @PathVariable int id) {

        Cars tempCar = carsService.findById(id);

        if (tempCar == null) {
            throw new RuntimeException("Car id not found - " + id);
        }

        carsService.deleteById(id);
        model.addAttribute("carslist", carsService.findAll());
        return "carslist";
    }
}

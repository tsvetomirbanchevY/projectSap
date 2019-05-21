package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.List;


@Controller
@RequestMapping(path = "/trips")
public class TripsController {

    private TripsService tripsService;


    public TripsController()
    {
        //default
    }

    @Autowired
    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @GetMapping("/findalltrips")
    public String findAll(Model model) {
        model.addAttribute("tripslist", tripsService.findAll());

        return "tripslist";
    }

    @GetMapping("/trips/{id}")
    public String getTrip(Model model, @PathVariable int id) {

        Trips trip = tripsService.findById(id);

        if (trip == null) {
            throw new RuntimeException("Trip id not found - " + id);
        }
        model.addAttribute("tripslist", tripsService.findAll());

        return "tripslist";
    }


    @GetMapping("/addtripsform")
    public String addTripsForm(Model model) {
        Trips trip= new Trips();
        model.addAttribute("trips", trip);
        return "addtripsform";
    }

    @PostMapping("/addtrips")
    public String addTrip(@ModelAttribute("trips") Trips trip) {
        trip.setId(0);
        tripsService.save(trip);
        return "endtrip";
    }


    @GetMapping("/updatetripsform/{id}")
    public String updateTripsForm(@PathVariable("trips") int id, Model model) {
        Trips trip =tripsService.findById(id);
        if (trip == null) {
            throw new RuntimeException("Trip id not found - " + id);
        }

        model.addAttribute("trips", trip);
        return "updatetripsform";
    }

    @PostMapping("/updatetrips/{id}")
    public String updateTrip(@PathVariable("id") int id, Model model, @ModelAttribute("trips") Trips trip) {

        tripsService.update(trip);
        model.addAttribute("tripslist", tripsService.findAll());

        return "";
    }


    @PostMapping("/deletetrips/{id}")
    public String deleteTrip(Model model, @PathVariable int id) {

        Trips tempTrip = tripsService.findById(id);

        if (tempTrip == null) {
            throw new RuntimeException("Trip id not found - " + id);
        }

        tripsService.deleteById(id);
        model.addAttribute("tripslist", tripsService.findAll());
        return "tripslist";
    }
}
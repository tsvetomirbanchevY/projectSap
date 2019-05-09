package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "/roles")
public class RolesController {

    private RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/findallroles")
    public List<Roles> findAll() {
        return rolesService.findAll();
    }

   /* @GetMapping("/roles/{id}")
    public Roles getRole(@PathVariable int id) {

        Roles role = rolesService.findById(id);

        if (role == null) {
            throw new RuntimeException("Role id not found - " + id);
        }

        return role;
    }

    @GetMapping("/addrolesform")
    public String addUsersForm(Model model) {
        Roles boss = new Roles();

        model.addAttribute("boss", boss);

        return "addrolesform";
    }

    @PostMapping("/addroles")
    public String addBoss(Model model, @RequestBody Roles role) {

        role.setId(0);
        rolesService.save(role);
        model.addAttribute("roleslist", rolesService.findAll());
        return "roleslist";
    }


   @PostMapping("/updateroles")
    public String updateRole(Model model, @RequestBody Roles role) {

        rolesService.save(role);
        model.addAttribute("roleslist", rolesService.findAll());
        return "roleslist";
    }


   @GetMapping("/deleteboss/{id}")
    public String deleteBoss(Model model, @PathVariable int id) {

       Roles tempBoss = rolesService.findById(id);

        if (tempBoss == null) {
            throw new RuntimeException("Role id not found - " + id);
        }

        rolesService.deleteById(id);
        model.addAttribute("roleslist", rolesService.findAll());
        return "roleslist";
    }*/

}
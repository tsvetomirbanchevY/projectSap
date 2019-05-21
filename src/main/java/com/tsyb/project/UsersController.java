package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private UsersService usersService;

    public UsersController()
    {
        //default
    }

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/findallusers")
    public String findAll(Model model) {
        model.addAttribute("userslist", usersService.findAll());
         return "userslist";
    }

    @GetMapping("/users/{id}")
    public String getUsers(Model model, @PathVariable int id) {

        Users user = usersService.findById(id);
        if (user == null) {
            throw new RuntimeException("User id not found - " + id);
        }
        model.addAttribute("userslist", user);
        return "userslist";
    }

    @GetMapping("/addusersform")
    public String addUsersForm(Model model) {
        Users user = new Users();

        model.addAttribute("users", user);

        return "addusersform";
    }

    @PostMapping("/addusers")
    public String addUser(@ModelAttribute("users") Users user, BindingResult bindingResult) {
        Users userExists = usersService.findByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the email provided");
        }
        user.setId(0);
        usersService.save(user);
        return "/home";
    }

    @GetMapping("/updateusersform/{id}")
    public String updateUsersForm(@PathVariable("id") int id, Model model) {
        Users user = usersService.findById(id);
        if (user == null) {
            throw new RuntimeException("User id not found - " + id);
        }

        model.addAttribute("users", user);
        return "updateusersform";
    }

    @PostMapping("/updateusers/{id}")
    public String updateUser(Model model, @ModelAttribute("users") Users user, @PathVariable("id") int id) {
        usersService.save(user);
        model.addAttribute("userslist", usersService.findAll());
        return "userslist";

    }

    @GetMapping("/deleteusers/{id}")
    public String deleteUser(Model model, @PathVariable int id) {

        Users tempUser = usersService.findById(id);

        if (tempUser == null) {
            throw new RuntimeException("User id not found - " + id);
        }

        usersService.deleteById(id);
        model.addAttribute("userslist", usersService.findAll());
        return "userslist";
    }

}
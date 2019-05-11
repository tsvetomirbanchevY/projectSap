package com.tsyb.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model){
        return "loginform";
    }

    @GetMapping("/home")
    public String Home(Model model){
        return "home";
    }

}

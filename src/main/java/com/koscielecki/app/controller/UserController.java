package com.koscielecki.app.controller;

import com.koscielecki.app.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/form")
    public String hello(Model model) {
        model.addAttribute("user",new User());
        return "form";
    }

}

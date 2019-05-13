package com.koscielecki.app.controller;

import com.koscielecki.app.Entity.User;
import com.koscielecki.app.Repository.UserRepository;
import com.koscielecki.app.Service.UserService;
import com.koscielecki.app.validation.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
//@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, BindingResult result, Model model, Locale locale, HttpServletRequest request){


        User userExist = userRepository.findOneByEmail(user.getEmail());

        new RegisterValidator().validateEmailExist(userExist, result);

        new RegisterValidator().validate(user, result);

        if (result.hasErrors()) {
            return "register";

        }else{

            userService.saveUser(user);


        }
        return "login";
    }
}

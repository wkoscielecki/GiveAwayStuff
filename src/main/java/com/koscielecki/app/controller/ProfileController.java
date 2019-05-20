package com.koscielecki.app.controller;

import com.koscielecki.app.Entity.User;


import com.koscielecki.app.Service.UserProfile;
import com.koscielecki.app.Service.UserService;
import com.koscielecki.app.validation.ChangePasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;


@Controller

public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfile(Model model) {
        String username= UserProfile.getLoggedUser();
        User user=userService.findOneByEmail(username);

        int nrRoli=user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);

        model.addAttribute("user",user);
        return "profile" ;
    }
    @RequestMapping(value = "/editPassword",method = RequestMethod.GET)
    public String editPassword(Model model){
        String username=UserProfile.getLoggedUser();
        User user=userService.findOneByEmail(username);
        model.addAttribute("user",user);
        return "editPassword";

    }
    @RequestMapping(value = "/updatePass",method = RequestMethod.POST)
    public String updatePassword(User user, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        new ChangePasswordValidator().validate(user, result);
        if (result.hasErrors()) {
            returnPage = "editPassword";
        } else {
            userService.updateUserPassword(user.getNewPassword(), user.getEmail());
            returnPage = "editPassword";
            model.addAttribute("message", messageSource.getMessage("passwordChange.success", null, locale));
        }
        return returnPage;
    }

    }



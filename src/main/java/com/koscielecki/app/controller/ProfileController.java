package com.koscielecki.app.controller;

import com.koscielecki.app.Entity.User;
import com.koscielecki.app.Service.CurrentUser;

import com.koscielecki.app.Service.UserProfile;
import com.koscielecki.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

public class ProfileController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfile(Model model) {
        String username= UserProfile.getLoggedUser();
        User user=userService.findOneByEmail(username);

        int nrRoli=user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);

        model.addAttribute("user",user);
        return "profile" ;
    }

}

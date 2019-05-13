package com.koscielecki.app.controller;

import com.koscielecki.app.Entity.User;
import com.koscielecki.app.Service.CurrentUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "this is user id " +entityUser.getId() ;
    }

}

package com.koscielecki.app.controller;

import com.koscielecki.app.Entity.User;


import com.koscielecki.app.Repository.UserRepository;
import com.koscielecki.app.Service.UserProfile;
import com.koscielecki.app.Service.UserService;
import com.koscielecki.app.validation.ChangePasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Locale;


@Controller

public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfile(Model model) {
        String username= UserProfile.getLoggedUser();
        User user=userService.findByEmail(username);

        int nrRoli=user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);

        model.addAttribute("user",user);
        return "profile" ;
    }

    @RequestMapping("/form")
    public String newUser(Model model) {
        User user=new User();
        model.addAttribute("user",user);
        return "form";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user")User user){
        userService.save(user);
        return "redirect:/";
    }
    @RequestMapping(value = "/editPassword",method = RequestMethod.GET)
    public String editPassword(Model model){
        String username=UserProfile.getLoggedUser();
        User user=userService.findByEmail(username);
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
            userService.updatePassword(user.getNewPassword(), user.getEmail());
            returnPage = "editPassword";
            model.addAttribute("message", messageSource.getMessage("passwordChange.success", null, locale));
        }
        return returnPage;
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id){
        ModelAndView mav=new ModelAndView("edit");
        User user=userService.get(id);
        mav.addObject("user",user);
        return mav;
    }
//    @PostMapping("/edit/{id}")
//    public String editUser(@Valid User user, BindingResult error, @PathVariable Long id, HttpServletRequest request){
//        if(error.hasErrors()){
//            return "form";
//        }
//        userService.save(user);
//        return "redirect:"+request.getContextPath()+"/profile";
//    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable (name = "id") Long id) {
       userService.delete(id);
        return "redirect:/userList";
    }
    @RequestMapping("/userList")
    public String userList(Model model){
        List<User> users=userService.listAll();
        model.addAttribute("users", users);

        return "userList";
    }
    }



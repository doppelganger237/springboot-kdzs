package com.zhang.kdzs.system.controller;

import com.zhang.kdzs.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value ={"/index"})
    public String index(Model model) {

       model.addAttribute("userNum",userService.getUserNum());
        return "index";
    }
    @RequestMapping(value ={"/","/home"})
    public String toindex() {
        // model.addAttribute("username","zjw");

        return "redirect:/index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}

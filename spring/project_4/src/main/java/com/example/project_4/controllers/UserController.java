package com.example.project_4.controllers;

import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.view.ThymeleafView;


@Controller
public class UserController {

    @GetMapping("/login")
    public ThymeleafView logIn() {
        ThymeleafView view = new ThymeleafView("Login"); 
        return view;
    }

}

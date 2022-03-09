package com.javatechie.ps.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/home")
    public String userPage(){
        return "payment home";
    }

}

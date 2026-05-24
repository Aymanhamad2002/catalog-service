package com.polarbookshop.catalogservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalogservice.config.PolarProperties;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
class HomeController {

    private final PolarProperties polarProperties;
    public HomeController(PolarProperties polarProperties){
        this.polarProperties = polarProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return  polarProperties.getGreeting();
    }
    

    
}
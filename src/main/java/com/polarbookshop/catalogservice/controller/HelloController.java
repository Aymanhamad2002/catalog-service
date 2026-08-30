package com.polarbookshop.catalogservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalogservice.config.PolarProperties;

@RestController
@RequestMapping("hello")
public class HelloController {
    private final PolarProperties polarProperties;
    

    public HelloController(PolarProperties polarProperties) {
        this.polarProperties = polarProperties;
    }


    @GetMapping
    public String hello() {
        return  polarProperties.getGreeting() ;
    }
}

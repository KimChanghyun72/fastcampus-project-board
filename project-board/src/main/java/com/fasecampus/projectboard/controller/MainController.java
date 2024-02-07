package com.fasecampus.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        //return "redirect:/articles";
        return "forward:/articles";
    }
}

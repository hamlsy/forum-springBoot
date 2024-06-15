package com.hamlsy.springForum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueController {
    @GetMapping("/vue")
    public String home(){
        return "index.html";
    }

}

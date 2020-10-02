package com.example.Project_Spring.controllers;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//
//@NoArgsConstructor
@AllArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {

        return "index";
    }


}
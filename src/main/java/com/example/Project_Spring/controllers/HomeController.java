package com.example.Project_Spring.controllers;



import com.example.Project_Spring.services.NewsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//
//@NoArgsConstructor
@AllArgsConstructor
@Controller
public class HomeController {

    NewsServices newsServices;


    @GetMapping("/")
    public String homePage(Model model) {

        model.addAttribute("news_list", newsServices.findAllNews());

        return "index";
    }


}
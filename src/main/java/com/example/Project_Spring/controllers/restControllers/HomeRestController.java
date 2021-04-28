package com.example.Project_Spring.controllers.restControllers;


import com.example.Project_Spring.models.News;
import com.example.Project_Spring.services.NewsServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = {"http://localhost:8082"})
@RestController
@AllArgsConstructor
@RequestMapping(produces = {"application/json"})
public class HomeRestController {

    NewsServices newsServices;

    @RequestMapping(method = RequestMethod.GET , value = {"/restNews"})
    public List<News> homePage(Model model) {

        return newsServices.findAllNewsSortedByDate();

    }

    @RequestMapping(method = RequestMethod.GET , value = {"/rest/news"})
    public List<News> homePageForLoggedUsers(Model model) {
        return newsServices.findAllNewsSortedByDate();
    }


}

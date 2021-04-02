package com.example.Project_Spring.controllers.restControllers;

import com.example.Project_Spring.models.News;
import com.example.Project_Spring.services.NewsServices;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(produces = {"application/json"})
public class NewsRestController {


    NewsServices newsServices;

    @RequestMapping(method = RequestMethod.GET , value = {"/readnews"})
    public Optional<News> getOneNewsFromDatabase(Model model, @RequestParam(value = "id") Long id) {


        return newsServices.findNewsById(id);
    }


}

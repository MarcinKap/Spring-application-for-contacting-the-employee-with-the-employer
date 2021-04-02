package com.example.Project_Spring.controllers.restControllers;

import com.example.Project_Spring.mappers.SavingsIdeasMapper;
import com.example.Project_Spring.models.News;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.services.*;
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
public class SavingsIdeasRestController {

    SavingIdeasCategoriesService savingIdeasCategoriesService;
    WorkAreasServices workAreasServices;
    TypeOfCostsServices typeOfCostsServices;

    SavingsIdeasMapper savingsIdeasMapper;
    SavingsIdeasServices savingsIdeasServices;
    IdeasRatingServices ideasRatingServices;
    CustomUserService customUserService;

    NewsServices newsServices;

    @RequestMapping(method = RequestMethod.GET , value = {"/getSavingsIdeasRest"})
    public List<SavingsIdeas> homePage(Model model) {


//        return newsServices.findAllNewsSortedByDate();
        return savingsIdeasServices.findAllSavingsIdeas();
    }

}

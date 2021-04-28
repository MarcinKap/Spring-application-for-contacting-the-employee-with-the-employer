package com.example.Project_Spring.controllers.restControllers;

import com.example.Project_Spring.mappers.SavingsIdeasMapper;
import com.example.Project_Spring.models.News;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST , value = {"/rest/sendRating"})
    public void saveRating(Model model,
                           @RequestParam("ratingValue") int rating,
                           @RequestParam("savingsIdeaId") Long savingsIdeaId,
                           @RequestParam("senderId") Long userId
                           ) {


        ideasRatingServices.SaveIdeaRating(savingsIdeaId, rating, userId);



    }




}

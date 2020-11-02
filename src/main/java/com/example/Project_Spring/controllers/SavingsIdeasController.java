package com.example.Project_Spring.controllers;


import com.example.Project_Spring.mappers.SavingsIdeasMapper;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.services.*;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class SavingsIdeasController {


    SavingIdeasCategoriesService savingIdeasCategoriesService;
    WorkAreasServices workAreasServices;
    TypeOfCostsServices typeOfCostsServices;

    SavingsIdeasMapper savingsIdeasMapper;
    SavingsIdeasServices savingsIdeasServices;
    IdeasRatingServices ideasRatingServices;
    CustomUserService customUserService;


    @GetMapping("/savings-ideas/savings-ideas-main-page")
    public String forumMainPage(Model model) {


        model.addAttribute("categories", savingIdeasCategoriesService.getAllCategories());
        model.addAttribute("workAreas", workAreasServices.getAllWorkAreas());
        model.addAttribute("typesofCosts", typeOfCostsServices.getAllTypesOfCosts());
        SavingsIdeas savingsIdea = new SavingsIdeas();
        model.addAttribute("savingIdea", savingsIdea);
//        private WorkArea workArea;
//        private TypeOfCosts typeOfCosts;
//
//        System.out.println("srednia arytmetyczna" +  savingsIdeasServices.findAllSavingsIdeas().get(1).getAverageRating());

        model.addAttribute("savingIdeasList", savingsIdeasServices.findAllSavingsIdeas());

        return "savings-ideas/savings-ideas-main-page";
    }


    //    zapisywanie danych bez reloadu
    @PostMapping("savings-ideas/savings-ideas-main-page/saveRating")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveRating(@RequestParam("value") int rating,
                           @RequestParam("id") Long savingsIdeaId) {

        Long userId = customUserService.getLoggedUsersId();

        ideasRatingServices.SaveIdeaRating(userId, savingsIdeaId, rating);
//teraz powinien być update średniej właśnie tego pomyslu


    }


    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/addSavingsIdea")
    public String addSavingsIdea(@ModelAttribute SavingsIdeas savingsIdea,
                                 @Param("workAreaId") Long workAreaId,
                                 @Param("categoryId") Long categoryId,
                                 @Param("4") String typeOfCost) {
        savingsIdea = savingsIdeasServices.savingsIdeasCompletingTheForm(savingsIdea, workAreaId, categoryId);

        savingsIdeasServices.saveSavingsIdea(savingsIdea);

        return "redirect:/savings-ideas/savings-ideas-main-page";
    }

    @RequestMapping(value = "/replace", method = RequestMethod.GET)
    public String replace(Model model) {

        model.addAttribute("savingIdeasList", savingsIdeasServices.findAllSavingsIdeas());// sending the row as list, so that the loop can iterate and will produce a single row
        return "savings-ideas/savings-ideas-main-page :: entity-row";
    }



    @GetMapping("/savings-ideas/savings-ideas-form")
    public String savingIdeasForm(Model model) {


        model.addAttribute("categories", savingIdeasCategoriesService.getAllCategories());
        model.addAttribute("workAreas", workAreasServices.getAllWorkAreas());
        model.addAttribute("typesofCosts", typeOfCostsServices.getAllTypesOfCosts());
        SavingsIdeas savingsIdea = new SavingsIdeas();
        model.addAttribute("savingIdea", savingsIdea);
//        private WorkArea workArea;
//        private TypeOfCosts typeOfCosts;
//
//        System.out.println("srednia arytmetyczna" +  savingsIdeasServices.findAllSavingsIdeas().get(1).getAverageRating());

        model.addAttribute("savingIdeasList", savingsIdeasServices.findAllSavingsIdeas());

        return "savings-ideas/savings-ideas-form";
    }



}

package com.example.Project_Spring.services;


import com.example.Project_Spring.models.IdeasRating;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.repositories.IdeasRatingRepository;
import com.example.Project_Spring.security.CustomUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IdeasRatingServices {


    IdeasRatingRepository ideasRatingRepository;
    SavingsIdeasServices savingsIdeasServices;
    CustomUserService customUserService;


    public IdeasRating updateOrSaveIdeaRating(Long userId, Long ideaId, int rating ){

        System.out.println("asdasdasdasd");
//        System.out.println(userId + ideaId + rating);
        System.out.println("user id " + userId);
        System.out.println("ideaId" + ideaId);
        System.out.println("rating " + rating);
        return Optional
                .ofNullable(ideasRatingRepository.findIdeasRatingById(ideaId))
                .map(i -> {
                   i.setSavingsIdeas(savingsIdeasServices.findSavingIdeaById(ideaId));
                   i.setEvaluator(customUserService.findUserById(userId));
                   i.setRating(rating);
                   return ideasRatingRepository.save(i);
                })
                .orElse(null);
    }

    public void SaveIdeaRating(Long ideaId, int rating ){

        System.out.println("zapis ratingu");


        IdeasRating findedIdeasRating = ideasRatingRepository.findIdeasRatingById(ideaId);
        SavingsIdeas savingsIdeas = savingsIdeasServices.findSavingIdeaById(ideaId);

        if(findedIdeasRating != null){

            System.out.println("1");
            findedIdeasRating.setRating(rating);
            ideasRatingRepository.save(findedIdeasRating);
        }else{
            System.out.println("2");
            IdeasRating ideasRating = new IdeasRating();

            ideasRating.setRating(rating);
            ideasRating.setSavingsIdeas(savingsIdeas);
            ideasRating.setEvaluator(customUserService.getLoggedUser());
            ideasRatingRepository.save(ideasRating);
        }


//        Double averageRating = savingsIdeas.getRatingList().stream().filter(o -> o.getRating() > 10).mapToDouble(o -> o.getRating()).sum();
        double averageRating = 0.0;
        for (IdeasRating ideasRating: savingsIdeas.getRatingList()
             ) {
            averageRating+=ideasRating.getRating();
        }
        averageRating = averageRating/savingsIdeas.getRatingList().size();

        SavingsIdeas updatedSavingIdea = savingsIdeasServices.findSavingIdeaById(ideaId);
        updatedSavingIdea.setAverageRating(averageRating);
        savingsIdeasServices.updateSavingsIdea(updatedSavingIdea);


    }



}

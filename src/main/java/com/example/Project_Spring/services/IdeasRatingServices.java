package com.example.Project_Spring.services;


import com.example.Project_Spring.models.IdeasRating;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.repositories.IdeasRatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IdeasRatingServices {


    IdeasRatingRepository ideasRatingRepository;
    SavingsIdeasServices savingsIdeasServices;

    public IdeasRating findIdeaRatingByUserIdAndIdeaId (Integer userId, Long ideaId){


        return ideasRatingRepository.findIdeasRatingByUserIdAndAndIdeaId(userId, ideaId);
    }


    public IdeasRating updateOrSaveIdeaRating(Integer userId, Long ideaId, int rating ){

        System.out.println("asdasdasdasd");
//        System.out.println(userId + ideaId + rating);
        System.out.println("user id " + userId);
        System.out.println("ideaId" + ideaId);
        System.out.println("rating " + rating);
        return Optional
                .ofNullable(ideasRatingRepository.findIdeasRatingByUserIdAndAndIdeaId(userId, ideaId))
                .map(i -> {
                   i.setIdeaId(ideaId);
                   i.setUserId(userId);
                   i.setRating(rating);
                   return ideasRatingRepository.save(i);
                })
                .orElse(null);
    }

    public void SaveIdeaRating(Integer userId, Long ideaId, int rating ){



        IdeasRating findedIdeasRating = findIdeaRatingByUserIdAndIdeaId(userId,ideaId);


        if(findedIdeasRating != null){
            findedIdeasRating.setRating(rating);

            ideasRatingRepository.save(findedIdeasRating);
        }else{
            IdeasRating ideasRating = new IdeasRating();

            ideasRating.setRating(rating);
            ideasRating.setIdeaId(ideaId);
            ideasRating.setUserId(userId);
            ideasRatingRepository.save(ideasRating);
        }

//        update średniej w tablicy saving ideas

        Double averageRating = ideasRatingRepository.findAverageRating(ideaId);
        SavingsIdeas updatedSavingIdea = savingsIdeasServices.findSavingIdeaById(ideaId);
        updatedSavingIdea.setAverageRating(averageRating);
        savingsIdeasServices.updateSavingsIdea(updatedSavingIdea);


    }



}

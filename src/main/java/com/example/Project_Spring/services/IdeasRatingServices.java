package com.example.Project_Spring.services;


import com.example.Project_Spring.models.IdeasRating;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.repositories.IdeasRatingRepository;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
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

    public void SaveIdeaRating(Long savingsIdeaId, int rating, Long userId){

        UserApp userApp = customUserService.findUserById(userId);
        saveIdeaRatingLogic(savingsIdeaId, rating, userApp);

    }

    public void SaveIdeaRating(Long savingsIdeaId, int rating){

        UserApp userApp = customUserService.getLoggedUser();
        saveIdeaRatingLogic(savingsIdeaId, rating, userApp);
    }


    void saveIdeaRatingLogic(Long savingsIdeaId, int rating, UserApp userApp){
        SavingsIdeas savingsIdeas = savingsIdeasServices.findSavingIdeaById(savingsIdeaId);


        //wyszukuje czy dany pomysł był już oceniany przez użytkownika
        IdeasRating findedIdeasRatingByUserIdAndSavingIdeaId = ideasRatingRepository.findIdeasRatingByUserAndSavingsIdeas(userApp, savingsIdeas);
        if (findedIdeasRatingByUserIdAndSavingIdeaId != null){
            findedIdeasRatingByUserIdAndSavingIdeaId.setRating(rating);
            ideasRatingRepository.save(findedIdeasRatingByUserIdAndSavingIdeaId);
        }else{
            IdeasRating ideasRating = new IdeasRating().builder()
                    .rating(rating)
                    .evaluator(userApp)
                    .savingsIdeas(savingsIdeas)
                    .build();
            ideasRatingRepository.save(ideasRating);
        }
        updateAveregeRatingSavingsIdea(savingsIdeaId);
    }

    void updateAveregeRatingSavingsIdea(Long savingsIdeaId){
        SavingsIdeas savingsIdeas = savingsIdeasServices.findSavingIdeaById(savingsIdeaId);
        double averageRating = 0.0;

        for (IdeasRating ideasRating: savingsIdeas.getRatingList()
        ) {
            averageRating+=ideasRating.getRating();
        }
        averageRating = averageRating/savingsIdeas.getRatingList().size();

        savingsIdeas.setAverageRating(averageRating);
        savingsIdeasServices.updateSavingsIdea(savingsIdeas);

//        SavingsIdeas updatedSavingIdea = savingsIdeasServices.findSavingIdeaById(savingsIdeaId);
//        updatedSavingIdea.setAverageRating(averageRating);
//        savingsIdeasServices.updateSavingsIdea(updatedSavingIdea);
//

    }


}

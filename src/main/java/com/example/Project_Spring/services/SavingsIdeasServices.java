package com.example.Project_Spring.services;


import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.repositories.IdeasRatingRepository;
import com.example.Project_Spring.repositories.SavingsIdeasRepository;
import com.example.Project_Spring.security.CustomUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SavingsIdeasServices {

    SavingsIdeasRepository savingsIdeasRepository;
    WorkAreasServices workAreasServices;
    SavingIdeasCategoriesService savingIdeasCategoriesService;
    CustomUserService customUserService;
    IdeasRatingRepository ideasRatingRepository;


    public SavingsIdeas saveSavingsIdea(SavingsIdeas savingsIdea) {
        return savingsIdeasRepository.save(savingsIdea);
    }


    public SavingsIdeas savingsIdeasCompletingTheForm(SavingsIdeas savingsIdeas, Long workAreaId, Long categoryId) {
        savingsIdeas.setWorkAreas(workAreasServices.getWorkAreasById(workAreaId));
        savingsIdeas.setSavingsIdeasCategories(savingIdeasCategoriesService.getCategoryById(categoryId));
        savingsIdeas.setDateOfCreation(LocalDateTime.now());
        savingsIdeas.setSender(customUserService.getLoggedUser());
        return savingsIdeas;
    }

    public List<SavingsIdeas> findAllSavingsIdeas() {
        return savingsIdeasRepository.findAll();
    }

    public SavingsIdeas findSavingIdeaById(Long id) {
        return savingsIdeasRepository.findById(id)
                .orElse(null);
    }

    public void updateSavingsIdea(SavingsIdeas savingsIdeas) {
        SavingsIdeas oldSavingIdea = findSavingIdeaById(savingsIdeas.getId());
        oldSavingIdea.setAverageRating(savingsIdeas.getAverageRating());
        savingsIdeasRepository.save(oldSavingIdea);
    }
}

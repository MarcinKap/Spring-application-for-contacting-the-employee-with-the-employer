package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.models.SavingsIdeasDto;
import org.springframework.stereotype.Component;

@Component
public class SavingsIdeasMapper implements Mapper<SavingsIdeas, SavingsIdeasDto>{


    @Override
    public SavingsIdeasDto map(SavingsIdeas from) {
        return SavingsIdeasDto
                .builder()
                .id(from.getId())
                .idea_subject(from.getIdea_subject())
                .description(from.getDescription())
                .benefits(from.getBenefits())
                .profitability(from.getProfitability())
                .unit(from.getUnit())
                .natureOfTheBenefit(from.getNatureOfTheBenefit())
                .savingsIdeasCategories(from.getSavingsIdeasCategories())
                .typeOfCosts(from.getTypeOfCosts())
                .workAreas(from.getWorkAreas())
                .dateOfCreation(from.getDateOfCreation())
                .averageRating(from.getAverageRating())
                .build();
    }

    @Override
    public SavingsIdeas reverseMap(SavingsIdeasDto to) {
        return SavingsIdeas
                .builder()
                .id(to.getId())
                .idea_subject(to.getIdea_subject())
                .description(to.getDescription())
                .benefits(to.getBenefits())
                .profitability(to.getProfitability())
                .unit(to.getUnit())
                .natureOfTheBenefit(to.getNatureOfTheBenefit())
                .savingsIdeasCategories(to.getSavingsIdeasCategories())
                .typeOfCosts(to.getTypeOfCosts())
                .workAreas(to.getWorkAreas())
                .dateOfCreation(to.getDateOfCreation())
                .averageRating(to.getAverageRating())
                .build();
    }


}

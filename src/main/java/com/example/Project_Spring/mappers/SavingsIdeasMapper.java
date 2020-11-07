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
                .ratingList(from.getRatingList())
                .ideaSubject(from.getIdeaSubject())
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
                .sender(from.getSender())
                .build();
    }

    @Override
    public SavingsIdeas reverseMap(SavingsIdeasDto to) {
        return SavingsIdeas
                .builder()
                .id(to.getId())
                .ratingList(to.getRatingList())
                .ideaSubject(to.getIdeaSubject())
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
                .sender(to.getSender())
                .build();
    }


}

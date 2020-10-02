package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.SavingsIdeasCategories;
import com.example.Project_Spring.models.SavingsIdeasCategoriesDto;
import org.springframework.stereotype.Component;

@Component
public class SavingsIdeasCategoriesMapper implements Mapper<SavingsIdeasCategories, SavingsIdeasCategoriesDto> {

    @Override
    public SavingsIdeasCategoriesDto map(SavingsIdeasCategories from) {
        return SavingsIdeasCategoriesDto
                .builder()
                .id(from.getId())
                .categoryName(from.getCategoryName())
                .savingsIdeas(from.getSavingsIdeas())
                .build();
    }

    @Override
    public SavingsIdeasCategories reverseMap(SavingsIdeasCategoriesDto to) {
        return SavingsIdeasCategories
                .builder()
                .id(to.getId())
                .categoryName(to.getCategoryName())
                .savingsIdeas(to.getSavingsIdeas())
                .build();
    }



}

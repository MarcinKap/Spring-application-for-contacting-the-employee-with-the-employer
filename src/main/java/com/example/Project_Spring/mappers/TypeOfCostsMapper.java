package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.TypeOfCosts;
import com.example.Project_Spring.models.TypeOfCostsDto;
import org.springframework.stereotype.Component;

@Component
public class TypeOfCostsMapper implements Mapper<TypeOfCosts, TypeOfCostsDto> {


    @Override
    public TypeOfCostsDto map(TypeOfCosts from) {
        return TypeOfCostsDto
                .builder()
                .id(from.getId())
                .name(from.getName())
                .savingsIdeas(from.getSavingsIdeas())
                .build();
    }

    @Override
    public TypeOfCosts reverseMap(TypeOfCostsDto to) {
        return TypeOfCosts
                .builder()
                .id(to.getId())
                .name(to.getName())
                .savingsIdeas(to.getSavingsIdeas())
                .build();
    }


}

package com.example.Project_Spring.mappers;

import com.example.Project_Spring.models.Survey;
import com.example.Project_Spring.models.SurveyDto;
import org.springframework.stereotype.Component;


@Component
public class SurveyMapper implements Mapper<Survey, SurveyDto> {

    @Override
    public SurveyDto map(Survey from) {
        return SurveyDto
                .builder()
                .id(from.getId())
                .question(from.getQuestion())
                .nazwa_ankiety(from.getNazwa_ankiety())
                .build();
    }

    @Override
    public Survey reverseMap(SurveyDto to) {
        return Survey
                .builder()
                .id(to.getId())
                .question(to.getQuestion())
                .nazwa_ankiety(to.getNazwa_ankiety())
                .build();
    }



}

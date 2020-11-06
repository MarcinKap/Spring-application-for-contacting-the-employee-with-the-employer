package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.Question;
import com.example.Project_Spring.models.QuestionDto;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper implements Mapper<Question, QuestionDto> {


    @Override
    public QuestionDto map(Question from) {
        return QuestionDto
                .builder()
                .id(from.getId())
                .idInquiry(from.getIdInquiry())
                .lista(from.getLista())
                .answer(from.getAnswer())
                .build();
    }

    @Override
    public Question reverseMap(QuestionDto to) {
        return Question
                .builder()
                .id(to.getId())
                .lista(to.getLista())
                .idInquiry(to.getIdInquiry())
                .answer(to.getAnswer())
                .build();
    }


}

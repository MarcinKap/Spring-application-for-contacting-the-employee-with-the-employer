package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.AnswerOrInquiry;
import com.example.Project_Spring.models.AnswerOrInquiryDto;
import org.springframework.stereotype.Component;

@Component
public class AnswerOrInquiryMapper implements Mapper<AnswerOrInquiry, AnswerOrInquiryDto> {

    @Override
    public AnswerOrInquiryDto map(AnswerOrInquiry from) {
        return AnswerOrInquiryDto
                .builder()
                .id(from.getId())
                .lista(from.getLista())
                .textMsg(from.getTextMsg())
                .build();
    }

    @Override
    public AnswerOrInquiry reverseMap(AnswerOrInquiryDto to) {
        return AnswerOrInquiry
                .builder()
                .id(to.getId())
                .lista(to.getLista())
                .textMsg(to.getTextMsg())
                .build();
    }


}




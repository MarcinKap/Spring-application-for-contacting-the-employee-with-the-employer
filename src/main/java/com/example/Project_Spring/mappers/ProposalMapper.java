package com.example.Project_Spring.mappers;

import com.example.Project_Spring.models.*;
import org.springframework.stereotype.Component;

@Component
public class ProposalMapper implements Mapper<Proposal, ProposalDto> {

    @Override
    public ProposalDto map(Proposal from) {
        return ProposalDto
                .builder()
                .id(from.getId())
                .senderId(from.getSenderId())
                .senderEmail(from.getSenderEmail())
                .nameAndSurname(from.getNameAndSurname())
                .textMessage(from.getTextMessage())
                .creationDate(from.getCreationDate())
                .topic(from.getTopic())
                .readed(from.isReaded())
                .build();
    }
    @Override
    public Proposal reverseMap(ProposalDto to) {
        return Proposal
                .builder()
                .id(to.getId())
                .senderId(to.getSenderId())
                .senderEmail(to.getSenderEmail())
                .nameAndSurname(to.getNameAndSurname())
                .textMessage(to.getTextMessage())
                .creationDate(to.getCreationDate())
                .topic(to.getTopic())
                .readed(to.isReaded())
                .build();
    }



}

package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.MessagesDto;
import org.springframework.stereotype.Component;

@Component
public class MessagesMapper implements Mapper<Messages, MessagesDto> {

    @Override
    public MessagesDto map(Messages from) {
        return MessagesDto
                .builder()
                .id(from.getId())
                .recipientId(from.getRecipientId())
                .senderId(from.getSenderId())
                .textMsg(from.getTextMsg())
                .createdDate(from.getCreatedDate())
                .build();
    }
    @Override
    public Messages reverseMap(MessagesDto to) {
        return Messages
                .builder()
                .id(to.getId())
                .recipientId(to.getRecipientId())
                .senderId(to.getSenderId())
                .textMsg(to.getTextMsg())
                .createdDate(to.getCreatedDate())
                .build();
    }
}
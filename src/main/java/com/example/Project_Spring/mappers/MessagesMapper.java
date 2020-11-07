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
                .textMsg(from.getTextMsg())
                .createdDate(from.getCreatedDate())
                .sender(from.getSender())
                .recipient(from.getRecipient())
                .build();
    }
    @Override
    public Messages reverseMap(MessagesDto to) {
        return Messages
                .builder()
                .id(to.getId())
                .textMsg(to.getTextMsg())
                .createdDate(to.getCreatedDate())
                .sender(to.getSender())
                .recipient(to.getRecipient())
                .build();
    }
}
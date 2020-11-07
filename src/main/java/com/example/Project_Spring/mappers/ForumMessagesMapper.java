package com.example.Project_Spring.mappers;

import com.example.Project_Spring.models.ForumMessages;
import com.example.Project_Spring.models.ForumMessagesDto;
import org.springframework.stereotype.Component;


@Component
public class ForumMessagesMapper implements Mapper<ForumMessages, ForumMessagesDto> {


    @Override
    public ForumMessagesDto map(ForumMessages from) {
        return ForumMessagesDto
                .builder()
                .id(from.getId())
                .textMsg(from.getTextMsg())
                .topic(from.getTopic())
                .createdDate(from.getCreatedDate())
                .sender(from.getSender())
                .build();
    }
    @Override
    public ForumMessages reverseMap(ForumMessagesDto to) {
        return ForumMessages
                .builder()
                .id(to.getId())
                .textMsg(to.getTextMsg())
                .topic(to.getTopic())
                .sender(to.getSender())
                .createdDate(to.getCreatedDate())
                .build();
    }


}

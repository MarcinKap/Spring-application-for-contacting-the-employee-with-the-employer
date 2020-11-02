package com.example.Project_Spring.mappers;

import com.example.Project_Spring.models.ForumMessages;
import com.example.Project_Spring.models.ForumMessagesDto;
import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.MessagesDto;
import org.springframework.stereotype.Component;


@Component
public class ForumMessagesMapper implements Mapper<ForumMessages, ForumMessagesDto> {


    @Override
    public ForumMessagesDto map(ForumMessages from) {
        return ForumMessagesDto
                .builder()
                .id(from.getId())
                .email_sender(from.getEmail_sender())
                .id_recipient(from.getId_recipient())
                .id_sender(from.getId_sender())
                .text_msg(from.getText_msg())
                .topic(from.getTopic())
                .id_topic(from.getId_topic())
                .createdDate(from.getCreatedDate())
                .nameSender(from.getNameSender())
                .build();
    }
    @Override
    public ForumMessages reverseMap(ForumMessagesDto to) {
        return ForumMessages
                .builder()
                .id(to.getId())
                .email_sender(to.getEmail_sender())
                .id_recipient(to.getId_recipient())
                .id_sender(to.getId_sender())
                .text_msg(to.getText_msg())
                .topic(to.getTopic())
                .id_topic(to.getId_topic())
                .createdDate(to.getCreatedDate())
                .nameSender(to.getNameSender())
                .build();
    }


}

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
                .senderEmail(from.getSenderEmail())
                .recipientId(from.getRecipientId())
                .senderId(from.getSenderId())
                .textMsg(from.getTextMsg())
                .topic(from.getTopic())
                .id_topic(from.getTopicId())
                .createdDate(from.getCreatedDate())
                .nameSender(from.getNameSender())
                .build();
    }
    @Override
    public ForumMessages reverseMap(ForumMessagesDto to) {
        return ForumMessages
                .builder()
                .id(to.getId())
                .senderEmail(to.getSenderEmail())
                .recipientId(to.getRecipientId())
                .senderId(to.getSenderId())
                .textMsg(to.getTextMsg())
                .topic(to.getTopic())
                .topicId(to.getId_topic())
                .createdDate(to.getCreatedDate())
                .nameSender(to.getNameSender())
                .build();
    }


}

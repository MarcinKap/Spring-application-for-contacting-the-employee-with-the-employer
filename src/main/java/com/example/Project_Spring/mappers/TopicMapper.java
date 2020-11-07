package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.Topic;
import com.example.Project_Spring.models.TopicDto;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper implements Mapper<Topic, TopicDto> {


    @Override
    public TopicDto map(Topic from) {
        return TopicDto
                .builder()
                .id(from.getId())
                .topic(from.getTopic())
                .text(from.getText())
                .dateOfCreation(from.getDateOfCreation())
                .numberOfForumMessages(from.getNumberOfForumMessages())
                .sender(from.getSender())
                .assignedForumMessages(from.getAssignedForumMessages())
                .build();
    }

    @Override
    public Topic reverseMap(TopicDto to) {
        return Topic
                .builder()
                .id(to.getId())
                .topic(to.getTopic())
                .text(to.getText())
                .sender(to.getSender())
                .dateOfCreation(to.getDateOfCreation())
                .numberOfForumMessages(to.getNumberOfForumMessages())
                .build();
    }



}

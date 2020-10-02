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
                .id_sender(from.getId_sender())
                .id_recipient(from.getId_recipient())
                .mailToDisplay(from.getMailToDisplay())
                .creatorName(from.getCreatorName())
                .dateOfCreation(from.getDateOfCreation())
                .forumTopic(from.getForumTopic())
                .topicFirstMsg(from.getTopicFirstMsg())
                .build();
    }

    @Override
    public Topic reverseMap(TopicDto to) {
        return Topic
                .builder()
                .id(to.getId())
                .topic(to.getTopic())
                .id_sender(to.getId_sender())
                .id_recipient(to.getId_recipient())
                .mailToDisplay(to.getMailToDisplay())
                .creatorName(to.getCreatorName())
                .dateOfCreation(to.getDateOfCreation())
                .forumTopic(to.getForumTopic())
                .topicFirstMsg(to.getTopicFirstMsg())
                .build();
    }



}

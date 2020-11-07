package com.example.Project_Spring.services;


import com.example.Project_Spring.mappers.TopicMapper;
import com.example.Project_Spring.models.Topic;
import com.example.Project_Spring.repositories.TopicRepository;
import com.example.Project_Spring.security.CustomUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicServices {

//    ForumMessagesService forumMessagesService;
    TopicMapper topicMapper;
    TopicRepository topicRepository;
//    CustomUserService customUserService;

    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public Topic getTopicByText(String text) {
        return topicRepository.findTopicByText(text);
    }

    public Topic getTopicById(Long id) {
        return topicRepository.findTopicById(id);
    }

    public List<Topic> findAllTopics(){
        return topicRepository.findAll();
    }

    public void deleteTopicsById(Long id) {
        topicRepository.deleteTopicById(id);
    }

    public void updateNumberOfForumMessages(Long id) {
        Topic topic = getTopicById(id);
        topic.setNumberOfForumMessages(topic.getAssignedForumMessages().size());
        topicRepository.save(topic);
    }


    public void updateNumberOfForumMessages(Topic topic) {
        int numberOfForumMessages = topic.getAssignedForumMessages().size();
        topic.setNumberOfForumMessages(numberOfForumMessages);
        topicRepository.save(topic);
    }

}

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

    ForumMessagesService forumMessagesService;
    TopicMapper topicMapper;
    TopicRepository topicRepository;

    CustomUserService customUserService;

    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public Topic getTopicByText(String text) {
        return topicRepository.findTopicByText(text);
    }

    public Topic getTopicById(Long id) {
        return topicRepository.findTopicById(id);
    }


    public List<Topic> getTopicsByLoggedIdUser() {

        Long loggedUserID = customUserService.getLoggedUsersId();
        List<Topic> topicsList = topicRepository.findTopicsByLoggedIdUserAndForumTopic(loggedUserID, false);

        for (int i = 0; i < topicsList.size(); i++) {


            if (topicsList.get(i).getSenderId() == null) {
                continue;
            }
            if (topicsList.get(i).getSenderId() == loggedUserID) {
                topicsList.get(i).setMailToDisplay(customUserService.getLoggedUsersEmail());
            } else {
                topicsList.get(i).setMailToDisplay(customUserService.findUserEmailAdressById(topicsList.get(i).getSenderId()));
            }
        }
        return topicsList;
    }

    public List<Topic> getForumThreads() {

        return topicRepository.findTopicsByForumTopicBoolean(true);
    }


    public void deleteTopicsById(Long id) {
        topicRepository.deleteTopicById(id);

//        return topicRepository.deleteTopicById(id) == 1;
    }

    public void updateNumberOfForumMessages(Long id) {
        Topic topic = getTopicById(id);
        int numberOfForumMessages = forumMessagesService.getNumberOfForumMessagesByTopicId(id);
        topic.setNumberOfForumMessages(numberOfForumMessages);
        topicRepository.save(topic);
    }
    public void updateNumberOfForumMessages(Topic topic) {
        int numberOfForumMessages = forumMessagesService.getNumberOfForumMessagesByTopicId(topic.getId());
        topic.setNumberOfForumMessages(numberOfForumMessages);
        topicRepository.save(topic);
    }

}

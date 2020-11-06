package com.example.Project_Spring.services;

import com.example.Project_Spring.mappers.ForumMessagesMapper;
import com.example.Project_Spring.models.ForumMessages;
import com.example.Project_Spring.repositories.ForumMessagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ForumMessagesService {

    ForumMessagesMapper forumMessagesMapper;
    ForumMessagesRepository forumMessagesRepository;

    public ForumMessages saveMessage(ForumMessages messages){

        return forumMessagesRepository.save(messages);
    }


    public List<ForumMessages> getForumMessagesByRecipientId(Integer id) {
        return forumMessagesRepository.findMessagesByRecipientId(id);
    }

    public List<ForumMessages> getForumMessagesBySenderId(Integer id) {
        return forumMessagesRepository.findMessagesBySenderId(id);
    }


    public List<ForumMessages> getForumMessagesByTopicId(Long id) {
        return forumMessagesRepository.findMessagesByTopicId(id);
    }

    public Optional<ForumMessages> getForumMessageById(Long id){
        return forumMessagesRepository.findById(id);
    }

    public void deleteForumMessageById(Long id){

        forumMessagesRepository.deleteMessagesById(id);

    }

    public int getNumberOfForumMessagesByTopicId(Long id){
        return forumMessagesRepository.findNumberOfForumMessagesByTopicId(id);
    }

    public int getNumberOfMessages(){
        return forumMessagesRepository.countNumberOfForumMessages();
    }



}

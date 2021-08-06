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


    public Optional<ForumMessages> getForumMessageById(Long id){
        return forumMessagesRepository.findById(id);
    }

    public void deleteForumMessageById(Long id){

        forumMessagesRepository.deleteMessagesById(id);

    }


    public int getNumberOfMessages(){
        return forumMessagesRepository.countNumberOfForumMessages();
    }



}

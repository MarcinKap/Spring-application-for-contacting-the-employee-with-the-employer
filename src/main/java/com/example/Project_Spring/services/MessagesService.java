package com.example.Project_Spring.services;

import com.example.Project_Spring.mappers.MessagesMapper;
import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.repositories.MessagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessagesService {


    MessagesMapper messagesMapper;
    MessagesRepository messageRepository;

    public Messages saveMessage(Messages messages){
        return messageRepository.save(messages);
    }


    public List<Messages> getMessagesByRecipientId(Integer id) {
        return messageRepository.findMessagesByRecipientId(id);
    }

    public List<Messages> getMessagesBySenderId(Integer id) {
        return messageRepository.findMessagesBySenderId(id);
    }


    public List<Messages> getMessagesByTopicId(Long id) {
        return messageRepository.findMessagesByTopicId(id);
    }


    public boolean deleteMessageById(Long id){

        return messageRepository.deleteMessagesById(id) == 1;
    }



}

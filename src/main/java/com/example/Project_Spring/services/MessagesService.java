package com.example.Project_Spring.services;

import com.example.Project_Spring.mappers.MessagesMapper;
import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.repositories.MessagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MessagesService {


    MessagesMapper messagesMapper;
    MessagesRepository messageRepository;

    public Messages saveMessage(Messages messages) {
        return messageRepository.save(messages);
    }


    public List<Messages> getMessagesByRecipientId(Long id) {
        return messageRepository.findMessagesByRecipientId(id);
    }

    public List<Messages> getMessagesBySenderId(Long id) {
        return messageRepository.findMessagesBySenderId(id);
    }


//    public List<Messages> getMessagesByTopicId(Long id) {
//        return messageRepository.findMessagesByTopicId(id);
//    }

    public List<Messages> getMessagesBySenderAndReceipientId(Long id, Long currentUserId) {
        List<Messages> messagesList = new ArrayList<>();
        messagesList.addAll(messageRepository.findMessagesByRecipientIdAndSenderId(id, currentUserId));
//        messagesList.addAll(messageRepository.findMessagesByRecipientIdAndSenderId(currentUserId, id));
        System.out.println("Lista wiadomości" + messagesList.size());

        return messagesList;
    }


    public boolean deleteMessageById(Long id) {

        return messageRepository.deleteMessagesById(id) == 1;
    }


    public Set<Long> getUniqueIdRecipientAndSender(Long id) {
        Set<Long> x = messageRepository.findRecipientIdBySenderId(id);
        Set<Long> y = messageRepository.findSenderIdByRecipientId(id);
        Set<Long> z = new HashSet<>();
        z.addAll(y);
        z.addAll(x);
        return z;
    }


}

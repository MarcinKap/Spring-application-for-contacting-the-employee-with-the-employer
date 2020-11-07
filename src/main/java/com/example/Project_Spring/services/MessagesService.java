package com.example.Project_Spring.services;

import com.example.Project_Spring.mappers.MessagesMapper;
import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.MessagesDto;
import com.example.Project_Spring.repositories.MessagesRepository;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class MessagesService {


    MessagesMapper messagesMapper;
    MessagesRepository messageRepository;
    CustomUserService customUserService;


    public Messages saveMessage(Messages messages) {
        return messageRepository.save(messages);
    }




    public List<Messages> findMessagesByUserApp(UserApp userApp){
        return messageRepository.findMessagesBySender(userApp);
    }


    public MessagesDto createMessage(String textMsg, Long recipientId ){

        MessagesDto messagesDto = MessagesDto.builder()
                .textMsg(textMsg)
                .createdDate(LocalDateTime.now())
                .sender(customUserService.getLoggedUser())
                .recipient(customUserService.findUserById(recipientId))
                .build();

        return messagesDto;
    }






    public boolean deleteMessageById(Long id) {

        return messageRepository.deleteMessagesById(id) == 1;
    }


    public List<Messages> getMessagesBySenderAndReceipientId(UserApp friend, UserApp currentLoggedUser) {
        Set<Messages> messagesSet = new HashSet<>();

        Set<Messages> sentMessages = currentLoggedUser.getSentMessagesList();
        for (Messages message: sentMessages
             ) {
            if(message.getRecipient().equals(friend))
                messagesSet.add(message);
        }
        Set<Messages> receivedMessages = currentLoggedUser.getReceivedMessagesList();
        for (Messages message: receivedMessages
        ) {
            if(message.getSender().equals(friend))
                messagesSet.add(message);
        }


        List<Messages> messagesList = new ArrayList<>(messagesSet);

        messagesList.sort(Comparator.comparing(Messages::getCreatedDate));

        return messagesList;
    }



}

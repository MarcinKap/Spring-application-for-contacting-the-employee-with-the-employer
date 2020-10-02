package com.example.Project_Spring.controllers;


import com.example.Project_Spring.mappers.MessagesMapper;
import com.example.Project_Spring.mappers.TopicMapper;
import com.example.Project_Spring.models.MessagesDto;
import com.example.Project_Spring.models.TopicDto;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
import com.example.Project_Spring.security.UserAppRepository;
import com.example.Project_Spring.services.MessagesService;
import com.example.Project_Spring.services.TopicServices;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class UserMenuController {


    private MessagesMapper messagesMapper;
    private MessagesService messagesService;

    private UserAppRepository userAppRepository;

    private TopicServices topicServices;
    private TopicMapper topicMapper;


    private CustomUserService customUserService;


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-index")
    public String accountIndex(Model model) {
        return "account-menu/account-index";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-questions")
    public String accountQuestions(Model model) {
        return "account-menu/account-questions";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-private-messages")
    public String accountPrivateMessages(Model model, @org.springframework.lang.Nullable @RequestParam("topicId") Long topicId) {

        Integer id = customUserService.getLoggedUsersId();

//        if(topicId ==null){
//            topicId= new Long(2);
//        }

        model.addAttribute("sended_messages", messagesService.getMessagesByTopicId(topicId));

        model.addAttribute("logged_in_user_id", id);
        model.addAttribute("current_id_message", topicId);
        model.addAttribute("topics", topicServices.getTopicsByLoggedIdUser());
        model.addAttribute("email_sender", customUserService.getLoggedUsersEmail());


        return "account-menu/account-private-messages";
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-sended-messages")
    public String accountSendedMessages(Model model) {
        Integer id = customUserService.getLoggedUsersId();
        model.addAttribute("sended_messages", messagesService.getMessagesBySenderId(id));
        return "account-menu/account-sended-messages";

    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-data")
    public String accountData(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());

        model.addAttribute("user", currentUser);
        return "account-menu/account-data";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/admin-panel")
    public String adminPanel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());

        model.addAttribute("user", currentUser);
        return "account-menu/admin-panel";
    }


    @PostMapping("/sendMessage")
    public String addMessage(Model model,
                             @org.springframework.lang.Nullable @RequestParam(value = "name") String name,
                             @RequestParam(value = "email_sender") String email_sender,
                             @RequestParam(value = "id_recipient") Integer id_recipient,
                             @RequestParam(value = "text_msg") String text_msg,
                             @org.springframework.lang.Nullable @RequestParam(value = "subject") String topic,
                             @org.springframework.lang.Nullable @RequestParam(value = "topicID") Long topicID
    ) {
        System.out.println("trololo");
        System.out.println(name);

        Integer id_sender = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserApp userApp = (UserApp) authentication.getPrincipal();
            id_sender = userApp.getId();
        }
        if(topic !=null) {
            TopicDto topicDto = TopicDto.builder()
                    .topic(topic)
                    .id_sender(id_sender)
                    .id_recipient(id_recipient)
                    .mailToDisplay(email_sender)
                    .build();

            topicServices.saveTopic(topicMapper.reverseMap(topicDto));
        }

        if(topicID==null){
            topicID = topicServices.getTopicByText(topic).getId();
        }
        MessagesDto messagesDto = MessagesDto.builder()
                .id_sender(id_sender)
                .id_recipient(id_recipient)
                .email_sender(email_sender)
                .text_msg(text_msg)
                .topic(topic)
                .id_topic(topicID)
                .createdDate(LocalDateTime.now())
                .build();
        messagesService.saveMessage(messagesMapper.reverseMap(messagesDto));

        return "redirect:/";
    }





    @PostMapping("/sendMessageToPrivateMessanger")
    public String addMessageToPrivateMessanger(Model model,
                                               @org.springframework.lang.Nullable @RequestParam(value = "name") String name,
                                               @RequestParam(value = "email_sender") String email_sender,
                                               @RequestParam(value = "id_recipient") Integer id_recipient,
                                               @RequestParam(value = "text_msg") String text_msg,
                                               @org.springframework.lang.Nullable @RequestParam(value = "subject") String topic,
                                               @Nullable @RequestParam(value = "topicID") Long topicId) {

        Integer id_sender = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserApp userApp = (UserApp) authentication.getPrincipal();
            id_sender = userApp.getId();
        }
        if(topic !=null) {
            TopicDto topicDto = TopicDto.builder()
                    .topic(topic)
                    .id_sender(id_sender)
                    .id_recipient(id_recipient)
                    .mailToDisplay(email_sender)
                    .build();
            topicServices.saveTopic(topicMapper.reverseMap(topicDto));
        }
        if(topicId==null){
            topicId = topicServices.getTopicByText(topic).getId();
        }

        MessagesDto messagesDto = MessagesDto.builder()
                .id_sender(id_sender)
                .id_recipient(id_recipient)
                .email_sender(email_sender)
                .text_msg(text_msg)
                .topic(topic)
                .id_topic(topicId)
                .createdDate(LocalDateTime.now())
                .build();
        messagesService.saveMessage(messagesMapper.reverseMap(messagesDto));


        return "redirect:/account-menu/account-private-messages?topicId=" + topicId;
    }










}

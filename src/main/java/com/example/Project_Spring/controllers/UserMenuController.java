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
    @GetMapping("/account-menu/account-sended-messages")
    public String accountSendedMessages(Model model) {
        Long id = customUserService.getLoggedUsersId();
//        model.addAttribute("sended_messages", messagesService.getMessagesBySenderId(id));
        return "account-menu/account-sended-messages";

    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-data")
    public String accountData(Model model) {


        model.addAttribute("current_logged_user", customUserService.getLoggedUser());
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


















}

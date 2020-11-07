package com.example.Project_Spring.controllers;

import com.example.Project_Spring.mappers.MessagesMapper;
import com.example.Project_Spring.mappers.TopicMapper;
import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.MessagesDto;
import com.example.Project_Spring.models.ServiceResponse;
import com.example.Project_Spring.models.TopicDto;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
import com.example.Project_Spring.security.UserAppRepository;
import com.example.Project_Spring.services.MessagesService;
import com.example.Project_Spring.services.TopicServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class MessagesController {
    private MessagesMapper messagesMapper;
    private MessagesService messagesService;

    private UserAppRepository userAppRepository;

    private TopicServices topicServices;
    private TopicMapper topicMapper;
    private CustomUserService customUserService;


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-private-messages")
    public String accountPrivateMessages(Model model, @org.springframework.lang.Nullable @RequestParam("friendId") Long friendId) {

        UserApp currentLoggedUser = customUserService.getLoggedUser();
        UserApp friend = customUserService.findUserById(friendId);
        model.addAttribute("friend", friend);
        model.addAttribute("current_logged_user", currentLoggedUser);
/*
        Lista osób z którymi można porozmawiać:
        - Zdobycie 2 list wiadomosci - wysłanych i otrzymanych -  zalogowanego użytkownika
        - Przejście po liscie wiadomosci zdobywając użytkowników zapisując je w SET
 */
        Set<UserApp> friendsList = customUserService.findUsersFriend(currentLoggedUser);
        model.addAttribute("users_list", friendsList);
        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friend, currentLoggedUser));



        return "account-menu/account-private-messages";
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/show-messages")
    public String showMessages(Model model, @org.springframework.lang.Nullable  @RequestParam("friendId") Long friendId) {

        UserApp currentLoggedUser = customUserService.getLoggedUser();
        UserApp friend = customUserService.findUserById(friendId);


        model.addAttribute("friend", friend);
        model.addAttribute("current_logged_user", customUserService.getLoggedUser());
/*
        Lista osób z którymi można porozmawiać:
        - Zdobycie 2 list wiadomosci - wysłanych i otrzymanych -  zalogowanego użytkownika
        - Przejście po liscie wiadomosci zdobywając użytkowników zapisując je w SET
 */
        Set<UserApp> friendsList = customUserService.findUsersFriend(currentLoggedUser);
        model.addAttribute("users_list", friendsList);
        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friend, currentLoggedUser));



        return "/account-menu/account-private-messages :: #showMessages";
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/update-messages")
    public String getUpdateMessages(Model model, @org.springframework.lang.Nullable  @RequestParam("friendId") Long friendId) {

        UserApp currentLoggedUser = customUserService.getLoggedUser();
        UserApp friend = customUserService.findUserById(friendId);
        model.addAttribute("friend", friend);
        model.addAttribute("current_logged_user", customUserService.getLoggedUser());

        /*
        Lista osób z którymi można porozmawiać:
        - Zdobycie 2 list wiadomosci - wysłanych i otrzymanych -  zalogowanego użytkownika
        - Przejście po liscie wiadomosci zdobywając użytkowników zapisując je w SET
 */
        Set<UserApp> friendsList = customUserService.findUsersFriend(currentLoggedUser);
        model.addAttribute("users_list", friendsList);
        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friend, currentLoggedUser));

        return "/account-menu/account-private-messages :: #updateMessages";
    }



    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/saveMessage")
    public ResponseEntity<Object> addPrivateMessage(@RequestParam(value = "recipientId") Long recipientId,
                                                    @RequestParam(value = "textMsg") String textMsg){

        MessagesDto messagesDto = messagesService.createMessage(textMsg, recipientId);

//                MessagesDto.builder()
//                .textMsg(textMsg)
//                .createdDate(LocalDateTime.now())
//                .sender(customUserService.findUserById(senderId))
//                .recipient(customUserService.findUserById(recipientId))
//                .build();

        messagesService.saveMessage(messagesMapper.reverseMap(messagesDto));

        ServiceResponse<Long> response = new ServiceResponse<Long>("success", recipientId);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }






}

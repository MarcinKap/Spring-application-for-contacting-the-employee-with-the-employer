package com.example.Project_Spring.controllers;

import com.example.Project_Spring.mappers.MessagesMapper;
import com.example.Project_Spring.mappers.TopicMapper;
import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.MessagesDto;
import com.example.Project_Spring.models.TopicDto;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
import com.example.Project_Spring.security.UserAppRepository;
import com.example.Project_Spring.services.MessagesService;
import com.example.Project_Spring.services.TopicServices;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.prepost.PreAuthorize;
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

        Long currentLoggedUserId = customUserService.getLoggedUsersId();

        //LiSTA UŻYTYKOWNIKÓW DO KTÓRYCH MOŻNA NAPISAĆ
        //Szukanie wszystkich id ludzi z którym aktualny użytkownik miał kontakt
        Set<Long> messagesUsersId = messagesService.getUniqueIdRecipientAndSender(currentLoggedUserId);
//      Szukanie wszystkich użytkowników po id - tych z którymi miał kontakt obecnie zalogowany użytkownik
        List<UserApp> userAppList = customUserService.getUserAppListById(messagesUsersId);
        //Użytkownicy do których można napisać
        model.addAttribute("users_list", userAppList);



        //Wiadomosci
        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friendId, currentLoggedUserId));
        //Friend
        model.addAttribute("friend", customUserService.findUserById(friendId));

        model.addAttribute("logged_user", customUserService.findUserById(currentLoggedUserId));

//        model.addAttribute("current_id_message", topicId);
//        model.addAttribute("topics", topicServices.getTopicsByLoggedIdUser());

        model.addAttribute("email_sender", customUserService.getLoggedUsersEmail());


        return "account-menu/account-private-messages";
    }


    @GetMapping(value = "/show-messages")
    public String getEventCount(Model model, @org.springframework.lang.Nullable  @RequestParam("friendId") Long friendId) {
//        Long currentLoggedUserId = customUserService.getLoggedUsersId();
//        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friendId, currentLoggedUserId));

        System.out.println(friendId + " frinedId");
        Long currentLoggedUserId = customUserService.getLoggedUsersId();

        //LiSTA UŻYTYKOWNIKÓW DO KTÓRYCH MOŻNA NAPISAĆ
        //Szukanie wszystkich id ludzi z którym aktualny użytkownik miał kontakt
        Set<Long> messagesUsersId = messagesService.getUniqueIdRecipientAndSender(currentLoggedUserId);
//      Szukanie wszystkich użytkowników po id - tych z którymi miał kontakt obecnie zalogowany użytkownik
        List<UserApp> userAppList = customUserService.getUserAppListById(messagesUsersId);
        //Użytkownicy do których można napisać
        model.addAttribute("users_list", userAppList);
        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friendId, currentLoggedUserId));
        model.addAttribute("friend", customUserService.findUserById(friendId));
        model.addAttribute("logged_user", customUserService.findUserById(currentLoggedUserId));
        model.addAttribute("email_sender", customUserService.getLoggedUsersEmail());

        return "/account-menu/account-private-messages :: #showMessages";
    }
    @GetMapping(value = "/update-messages")
    public String getUpdateMessages(Model model, @org.springframework.lang.Nullable  @RequestParam("friendId") Long friendId) {
//        Long currentLoggedUserId = customUserService.getLoggedUsersId();
//        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friendId, currentLoggedUserId));

        System.out.println(friendId + " frinedId");
        Long currentLoggedUserId = customUserService.getLoggedUsersId();

        //LiSTA UŻYTYKOWNIKÓW DO KTÓRYCH MOŻNA NAPISAĆ
        //Szukanie wszystkich id ludzi z którym aktualny użytkownik miał kontakt
        Set<Long> messagesUsersId = messagesService.getUniqueIdRecipientAndSender(currentLoggedUserId);
//      Szukanie wszystkich użytkowników po id - tych z którymi miał kontakt obecnie zalogowany użytkownik
        List<UserApp> userAppList = customUserService.getUserAppListById(messagesUsersId);
        //Użytkownicy do których można napisać
        model.addAttribute("users_list", userAppList);
        model.addAttribute("messages", messagesService.getMessagesBySenderAndReceipientId(friendId, currentLoggedUserId));
        model.addAttribute("friend", customUserService.findUserById(friendId));
        model.addAttribute("logged_user", customUserService.findUserById(currentLoggedUserId));
        model.addAttribute("email_sender", customUserService.getLoggedUsersEmail());

        return "/account-menu/account-private-messages :: #updateMessages";
    }




    @PostMapping("/sendMessageToPrivateMessanger")
    public String addMessageToPrivateMessanger(Model model,
                                               @RequestParam(value = "id_recipient") Long recipientId,
                                               @RequestParam(value = "text_msg") String text_msg
                                               ) {
        Long senderId = customUserService.getLoggedUsersId();
        MessagesDto messagesDto = MessagesDto.builder()
                .senderId(senderId)
                .recipientId(recipientId)
                .textMsg(text_msg)
                .createdDate(LocalDateTime.now())
                .build();

        messagesService.saveMessage(messagesMapper.reverseMap(messagesDto));


        return "redirect:/account-menu/account-private-messages?friendId=" + recipientId;
    }

}

package com.example.Project_Spring.controllers;

import com.example.Project_Spring.mappers.MessagesMapper;
import com.example.Project_Spring.mappers.TopicMapper;
import com.example.Project_Spring.models.MessagesDto;
import com.example.Project_Spring.models.TopicDto;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserAppRepository;
import com.example.Project_Spring.services.MessagesService;
import com.example.Project_Spring.services.TopicServices;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;


@AllArgsConstructor
@Controller
public class MenuController {


    private CustomUserService customUserService;
    private UserAppRepository userAppRepository;

    private TopicServices topicServices;
    private TopicMapper topicMapper;

    private MessagesService messagesService;
    private MessagesMapper messagesMapper;

    @GetMapping("/survey")
    public String survey(Model model) {
        return "surveys package/survey";
    }

    @GetMapping("/survey-selected")
    public String survey_selected(Model model, @Param("marks") String marks) {
        System.out.println(marks);
        return "surveys package/survey_selected";
    }

    @GetMapping("/applications-and-needs")
    public String applications_and_needs(Model model) {
        // ZDOBYWANIE EMAILA ZALOGOWANEGO UŻYTKOWNIKA I WYSYLANIE GO DO FORMULARZA NA STRONIE
        if (customUserService.getLoggedUsersEmail() != null) {
            model.addAttribute("email_sender", customUserService.getLoggedUsersEmail());
        }

        return "applications-and-needs package/applications-and-needs";
    }


    //    FORUM
    @GetMapping("/forum/forum-main-page")
    public String forumMainPage(Model model) {


        model.addAttribute("threads", topicServices.getForumThreads());
        model.addAttribute("currentLoggedUserId", customUserService.getLoggedUsersId());
        return "forum/forum-main-page";
    }


    //    GŁÓWNE MENU TEMATÓW
    @PostMapping("/forum/forum-thread-creator")
    public String forumThreadCreator(Model model,
                                     @RequestParam(value = "thread") String thread,
                                     @RequestParam(value = "thread_text") String threadText) {


        String creatorName = customUserService.getLoggedUserName();
        Integer creatorId = customUserService.getLoggedUsersId();

        TopicDto topicDto = TopicDto.builder()
                .topic(thread)
                .id_sender(creatorId)
                .creatorName(creatorName)
                .dateOfCreation(LocalDateTime.now())
                .forumTopic(true)
                .topicFirstMsg(threadText)
                .build();
        topicServices.saveTopic(topicMapper.reverseMap(topicDto));

        Long topicId = topicServices.getTopicByText(thread).getId();

        MessagesDto messagesDto = MessagesDto.builder()
                .id_sender(creatorId)
                .text_msg(threadText)
                .topic(thread)
                .id_topic(topicId)
                .createdDate(LocalDateTime.now())
                .nameSender(customUserService.getLoggedUserName())
                .build();
        messagesService.saveMessage(messagesMapper.reverseMap(messagesDto));

        return "redirect:/forum/forum-main-page";
    }


    //    POSZCZEGÓLNE TEMATY
    @GetMapping("/forum/forum-thread")
    public String forumThread(Model model,
                              @RequestParam(value = "topicId") Long topicId) {

        model.addAttribute("messages", messagesService.getMessagesByTopicId(topicId));
        model.addAttribute("topicId", topicId);
        model.addAttribute("currentLoggedUserId", customUserService.getLoggedUsersId());

        return "forum/forum-thread";
    }


    //    ZAPISYWANIE POSTÓW
    @PostMapping("/forum/forum-post-creator")
    public String forumPostCreator(Model model,
                                   @RequestParam(value = "message_text") String messageText,
                                   @RequestParam(value = "topicId") Long topicId
    ) {


        Integer creatorId = customUserService.getLoggedUsersId();


        MessagesDto messagesDto = MessagesDto.builder()
                .id_sender(creatorId)
                .text_msg(messageText)
                .id_topic(topicId)
                .createdDate(LocalDateTime.now())
                .nameSender(customUserService.getLoggedUserName())
                .build();
        messagesService.saveMessage(messagesMapper.reverseMap(messagesDto));


        return "redirect:/forum/forum-thread?topicId=" + topicId;
    }


    @GetMapping("/deleteMessage")
    public String deleteMessage(Model model,
                                @RequestParam(value = "messageIdToDelete") Long messageId,
                                @RequestParam(value = "topicId") Long topicId) {


        messagesService.deleteMessageById(messageId);

        return "redirect:/forum/forum-thread?topicId=" + topicId;
    }

    @GetMapping("/deleteThread")
    public String deleteThread(Model model,
                               @RequestParam(value = "threadIdToDelete") Long threadId
    ) {

        topicServices.deleteTopicsById(threadId);

        return "redirect:/forum/forum-main-page";
    }


// Koniec forum


}

package com.example.Project_Spring.controllers;

import com.example.Project_Spring.mappers.ForumMessagesMapper;
import com.example.Project_Spring.mappers.TopicMapper;
import com.example.Project_Spring.models.ForumMessagesDto;
import com.example.Project_Spring.models.Topic;
import com.example.Project_Spring.models.TopicDto;
import com.example.Project_Spring.security.CustomUserService;
import com.example.Project_Spring.security.UserApp;
import com.example.Project_Spring.security.UserAppRepository;
import com.example.Project_Spring.services.ForumMessagesService;
import com.example.Project_Spring.services.TopicServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;


@AllArgsConstructor
@Controller
public class ForumController {

    private TopicServices topicServices;
    private TopicMapper topicMapper;
    private CustomUserService customUserService;
    private ForumMessagesService forumMessagesService;
    private ForumMessagesMapper forumMessagesMapper;
    private UserAppRepository userAppRepository;


    @GetMapping("/forum/forum-main-page")
    public String forumMainPage(Model model) {
        model.addAttribute("all_posts", forumMessagesService.getNumberOfMessages());
        model.addAttribute("threads", topicServices.findAllTopics());
        model.addAttribute("currentLoggedUserId", customUserService.getLoggedUsersId());
        return "forum/forum-main-page";
    }

    @PostMapping("/forum/forum-thread-creator")
    public String forumThreadCreator(Model model,
                                     @RequestParam(value = "thread") String thread,
                                     @RequestParam(value = "thread_text") String threadText) {

        UserApp currentLoggedUser = customUserService.getLoggedUser();

        TopicDto topicDto = TopicDto.builder()
                .topic(thread)
                .text(threadText)
                .dateOfCreation(LocalDateTime.now())
                .sender(currentLoggedUser)
                .build();
        topicServices.saveTopic(topicMapper.reverseMap(topicDto));



        Long topicId = topicServices.getTopicByText(thread).getId();

        System.out.println(currentLoggedUser.getName());
        System.out.println("Dodanie topic");


        return "redirect:/forum/forum-main-page";
    }


    @GetMapping("/forum/forum-thread")
    public String forumThread(Model model,
                              @RequestParam(value = "topic") Long topicId) {



        model.addAttribute("currentLoggedUser", customUserService.getLoggedUsersId());
        model.addAttribute("topic", topicServices.getTopicById(topicId));
        return "forum/forum-thread";
    }





    //    ZAPISYWANIE POSTÓW
    @PostMapping("/forum/forum-post-creator")
    public String forumPostCreator(Model model,
                                   @RequestParam(value = "message_text") String messageText,
                                   @RequestParam(value = "topicId") Long topicId
    ) {


//      Long creatorId = customUserService.getLoggedUsersId();

        ForumMessagesDto forumMessagesDto = ForumMessagesDto.builder()
                .sender(customUserService.getLoggedUser())
                .topic(topicServices.getTopicById(topicId))
                .textMsg(messageText)
                .createdDate(LocalDateTime.now())
                .build();
        forumMessagesService.saveMessage(forumMessagesMapper.reverseMap(forumMessagesDto));

//        topicServices.updateNumberOfForumMessages(forumMessagesDto.getTopicId());

        topicServices.updateNumberOfForumMessages(topicId);

        return "redirect:/forum/forum-thread?topic=" + topicId;
    }


    @GetMapping("/deleteMessage")
    public String deleteMessage(Model model,
                                @RequestParam(value = "messageId") Long messageId,
                                @RequestParam(value = "topicId") Long topicId) {



        if(customUserService.getLoggedUsersId().equals(forumMessagesService.getForumMessageById(messageId).get().getSender().getId())) {
            forumMessagesService.deleteForumMessageById(messageId);
            topicServices.updateNumberOfForumMessages(topicId);
        }

        return "redirect:/forum/forum-thread?topic=" + topicId;
    }




    @GetMapping("/deleteThread")
    public String deleteThread(Model model,
                               @RequestParam(value = "threadIdToDelete") Long threadId
    ) {

        topicServices.deleteTopicsById(threadId);

        return "redirect:/forum/forum-main-page";
    }


}

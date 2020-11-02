package com.example.Project_Spring.controllers;

import com.example.Project_Spring.mappers.ForumMessagesMapper;
import com.example.Project_Spring.mappers.TopicMapper;
import com.example.Project_Spring.models.ForumMessagesDto;
import com.example.Project_Spring.models.Topic;
import com.example.Project_Spring.models.TopicDto;
import com.example.Project_Spring.security.CustomUserService;
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
import java.util.List;


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

        List<Topic> topicList = topicServices.getForumThreads();


        model.addAttribute("all_posts", forumMessagesService.getNumberOfMessages());
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
        Long creatorId = customUserService.getLoggedUsersId();

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

        ForumMessagesDto forumMessagesDto = ForumMessagesDto.builder()
                .id_sender(creatorId)
                .text_msg(threadText)
                .topic(thread)
                .id_topic(topicId)
                .createdDate(LocalDateTime.now())
                .nameSender(customUserService.getLoggedUserName())
                .build();
        forumMessagesService.saveMessage(forumMessagesMapper.reverseMap(forumMessagesDto));

        return "redirect:/forum/forum-main-page";
    }


    //    POSZCZEGÓLNE TEMATY
    @GetMapping("/forum/forum-thread")
    public String forumThread(Model model,
                              @RequestParam(value = "topicId") Long topicId) {

//        model.addAttribute("messages", forumMessagesService.getForumMessagesByTopicId(topicId));
//        model.addAttribute("mainTopic", forumMessagesService.getForumMessagesByTopicId(topicId));
//        model.addAttribute("topicId", topicId);
//

        model.addAttribute("currentLoggedUserId", customUserService.getLoggedUsersId());
        model.addAttribute("topic", topicServices.getTopicById(topicId));
        model.addAttribute("forumMessages", forumMessagesService.getForumMessagesByTopicId(topicId));

        System.out.println(forumMessagesService.getForumMessagesByTopicId(topicId).size());

        return "forum/forum-thread";
    }


    //    ZAPISYWANIE POSTÓW
    @PostMapping("/forum/forum-post-creator")
    public String forumPostCreator(Model model,
                                   @RequestParam(value = "message_text") String messageText,
                                   @RequestParam(value = "topicId") Long topicId
    ) {


        Long creatorId = customUserService.getLoggedUsersId();


        ForumMessagesDto forumMessagesDto = ForumMessagesDto.builder()
                .id_sender(creatorId)
                .text_msg(messageText)
                .id_topic(topicId)
                .createdDate(LocalDateTime.now())
                .nameSender(customUserService.getLoggedUserName())
                .build();
        forumMessagesService.saveMessage(forumMessagesMapper.reverseMap(forumMessagesDto));

        topicServices.updateNumberOfForumMessages(forumMessagesDto.getId_topic());


        topicServices.updateNumberOfForumMessages(topicId);

        return "redirect:/forum/forum-thread?topicId=" + topicId;
    }


    @GetMapping("/deleteMessage")
    public String deleteMessage(Model model,
                                @RequestParam(value = "messageId") Long messageId,
                                @RequestParam(value = "topicId") Long topicId) {


        if(customUserService.getLoggedUsersId().equals(forumMessagesService.getForumMessageById(messageId).get().getId_sender())) {
            forumMessagesService.deleteForumMessageById(messageId);
        }

        return "redirect:/forum/forum-thread?topicId=" + topicId;
    }

    @GetMapping("/deleteThread")
    public String deleteThread(Model model,
                               @RequestParam(value = "threadIdToDelete") Long threadId
    ) {

        topicServices.deleteTopicsById(threadId);

        return "redirect:/forum/forum-main-page";
    }


}

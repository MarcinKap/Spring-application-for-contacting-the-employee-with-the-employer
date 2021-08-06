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

        return "surveys package/survey_selected";
    }




    //    FORUM



// Koniec forum


}

package com.example.Project_Spring.controllers;

import com.example.Project_Spring.mappers.NewsMapper;
import com.example.Project_Spring.models.News;
import com.example.Project_Spring.services.NewsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class NewsController {

    NewsServices newsServices;

    @GetMapping("/news/news-main-page")
    public String forumMainPage(Model model,
                                @RequestParam(value = "id") Long id) {

//        model.addAttribute("title", title);
        Optional<News> news = newsServices.findNewsById(id);
        model.addAttribute("news", news);

        return "news/news-main-page";
    }


}

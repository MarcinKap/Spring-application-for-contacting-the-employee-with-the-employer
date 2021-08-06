package com.example.Project_Spring.controllers;

import com.example.Project_Spring.mappers.NewsMapper;
import com.example.Project_Spring.models.News;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.services.NewsServices;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class NewsController {

    NewsServices newsServices;

    @GetMapping("/news/news-main-page")
    public String forumMainPage(Model model,
                                @RequestParam(value = "id") Long id) {
        Optional<News> news = newsServices.findNewsById(id);
        model.addAttribute("news", news);
        return "news/news-main-page";
    }

    @GetMapping("/news/form-for-adding-a-news")
    public String formForAddingNews (Model model) {
        News news = new News();
        model.addAttribute("news", news);
        return "news/form-for-adding-a-news";
    }

    @PostMapping("/add-news")
    public String addSavingsIdea(@ModelAttribute News news) {
        news.setCreatedDate(LocalDateTime.now());
        newsServices.saveNews(news);
        return "redirect:/";
    }





}

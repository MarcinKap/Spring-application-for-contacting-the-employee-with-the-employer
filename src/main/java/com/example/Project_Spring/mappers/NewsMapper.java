package com.example.Project_Spring.mappers;

import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.MessagesDto;
import com.example.Project_Spring.models.News;
import com.example.Project_Spring.models.NewsDto;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper implements Mapper<News, NewsDto> {

    @Override
    public NewsDto map(News from) {
        return NewsDto
                .builder()
                .id(from.getId())
                .movie_link(from.getMovie_link())
                .text(from.getText())
                .title(from.getTitle())
                .build();
    }

    @Override
    public News reverseMap(NewsDto to) {
        return News
                .builder()
                .id(to.getId())
                .movie_link(to.getMovie_link())
                .text(to.getText())
                .title(to.getTitle())
                .build();
    }
}

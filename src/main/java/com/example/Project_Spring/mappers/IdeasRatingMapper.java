package com.example.Project_Spring.mappers;


import com.example.Project_Spring.models.IdeasRating;
import com.example.Project_Spring.models.IdeasRatingDto;
import org.springframework.stereotype.Component;

@Component
public class IdeasRatingMapper implements Mapper<IdeasRating, IdeasRatingDto> {

    @Override
    public IdeasRatingDto map(IdeasRating from) {
        return IdeasRatingDto
                .builder()
                .id(from.getId())
                .ideaId(from.getIdeaId())
                .userId(from.getUserId())
                .rating(from.getRating())
                .build();
    }
    @Override
    public IdeasRating reverseMap(IdeasRatingDto to) {
        return IdeasRating
                .builder()
                .id(to.getId())
                .ideaId(to.getIdeaId())
                .userId(to.getUserId())
                .rating(to.getRating())
                .build();
    }



}

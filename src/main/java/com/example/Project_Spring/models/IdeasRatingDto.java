package com.example.Project_Spring.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdeasRatingDto {

    private Long id;
    private Integer userId;
    private Long ideaId;
    private int rating;
}

package com.example.Project_Spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDto {

    private Long id;
    private String title;
    private String text;
    private String shortText;
    private String movie_link;
    private LocalDateTime createdDate;

}

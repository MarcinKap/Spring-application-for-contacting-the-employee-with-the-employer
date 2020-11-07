package com.example.Project_Spring.models;


import com.example.Project_Spring.security.UserApp;
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
    private int rating;
    private SavingsIdeas savingsIdeas;
    private UserApp evaluator;
}

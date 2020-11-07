package com.example.Project_Spring.models;


import com.example.Project_Spring.security.UserApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDto {

    private Long id;
    private String topic;
    private String text;
    private LocalDateTime dateOfCreation;
    private int numberOfForumMessages;
    private Set<ForumMessages> assignedForumMessages;
    private UserApp sender;


}

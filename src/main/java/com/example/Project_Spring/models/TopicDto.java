package com.example.Project_Spring.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDto {

    private Long id;
    private String topic;
    private Integer id_sender;
    private Integer id_recipient;
    private String mailToDisplay;
    private String creatorName;
    private LocalDateTime dateOfCreation;
    private Boolean forumTopic;
    private String topicFirstMsg;

}

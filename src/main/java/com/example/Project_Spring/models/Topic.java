package com.example.Project_Spring.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "topics")
public class Topic {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String topic;
        private Long senderId;
        private Long recipientId;
        private String mailToDisplay;
        private String creatorName;
        private Boolean forumTopic;
        private String topicFirstMsg;
        private LocalDateTime dateOfCreation;

        @Nullable
        private int numberOfForumMessages;



}

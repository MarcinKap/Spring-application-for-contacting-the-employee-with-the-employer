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
@Table(name = "forum_messages")
public class ForumMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long topicId;

    @Nullable
    private Long senderId;
    @Nullable
    private String senderEmail;

    private Long recipientId;

    private String textMsg;


    private String topic;

    private LocalDateTime createdDate;

    private String nameSender;





}

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

    private Long id_topic;

    @Nullable
    private Long id_sender;
    @Nullable
    private String email_sender;

    private Long id_recipient;

    private String text_msg;


    private String topic;

    private LocalDateTime createdDate;

    private String nameSender;





}

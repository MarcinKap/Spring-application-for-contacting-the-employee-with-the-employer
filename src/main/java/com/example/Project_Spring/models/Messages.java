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
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_topic;

    @Nullable
    private Integer id_sender;
    @Nullable
    private String email_sender;

    private Integer id_recipient;

    private String text_msg;


    private String topic;

    private LocalDateTime createdDate;

    private String nameSender;







}

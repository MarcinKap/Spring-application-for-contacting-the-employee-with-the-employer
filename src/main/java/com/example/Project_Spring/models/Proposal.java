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
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private Long senderId;
    @Nullable
    private String senderEmail;
    @Nullable
    private String nameAndSurname;
    private String textMessage;
    private String topic;
    private LocalDateTime creationDate;
    private boolean readed;



}

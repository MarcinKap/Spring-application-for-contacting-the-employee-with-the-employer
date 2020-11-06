package com.example.Project_Spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForumMessagesDto {
    private Long id;

    private Long id_topic;


    @Nullable
    private Long senderId;
    private String senderEmail;
    private Long recipientId;
    private String textMsg;
    private String topic;
    private String nameSender;

    private LocalDateTime createdDate;

}

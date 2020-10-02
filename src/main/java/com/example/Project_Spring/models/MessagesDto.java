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
public class MessagesDto {


    private Long id;

    private Long id_topic;


    @Nullable
    private Integer id_sender;
    private String email_sender;
    private Integer id_recipient;
    private String text_msg;
    private String topic;
    private String nameSender;

    private LocalDateTime createdDate;

}

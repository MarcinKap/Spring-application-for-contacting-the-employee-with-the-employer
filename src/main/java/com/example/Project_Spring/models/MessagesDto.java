package com.example.Project_Spring.models;

import com.example.Project_Spring.security.UserApp;
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
    private String textMsg;
    private LocalDateTime createdDate;
    private UserApp recipient;
    private UserApp sender;

}

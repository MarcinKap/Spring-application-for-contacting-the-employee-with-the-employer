package com.example.Project_Spring.models;

import com.example.Project_Spring.security.UserApp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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

    private String textMsg;
    @Nullable
    private LocalDateTime createdDate;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_sent_messages",
            joinColumns =
            @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserApp sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_received_messages",
            joinColumns =
            @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserApp recipient;




}

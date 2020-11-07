package com.example.Project_Spring.models;

import com.example.Project_Spring.security.UserApp;
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
    private String textMsg;
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_sent_forum_messages_by_user",
            joinColumns =
            @JoinColumn(name = "forum_message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserApp sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_messages_assigned_to_topic",
            joinColumns =
            @JoinColumn(name = "forum_message_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Topic topic;

}

package com.example.Project_Spring.models;

import com.example.Project_Spring.security.UserApp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


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
        private String text;
        private LocalDateTime dateOfCreation;
        @Nullable
        private int numberOfForumMessages;

        @JsonIgnore
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
        private Set<ForumMessages> assignedForumMessages;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinTable(name = "list_of_user_topics",
                joinColumns =
                @JoinColumn(name = "topic_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
        )
        private UserApp sender;


}

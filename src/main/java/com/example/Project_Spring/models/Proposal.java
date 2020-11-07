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
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_sent_proposals",
            joinColumns =
            @JoinColumn(name = "proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserApp sender;
    @Nullable
    private String senderEmail;
    @Nullable
    private String nameAndSurname;
    private String textMessage;
    private String topic;
    private LocalDateTime creationDate;
    private boolean readed;



}

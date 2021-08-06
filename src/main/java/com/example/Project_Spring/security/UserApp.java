package com.example.Project_Spring.security;

import com.example.Project_Spring.models.ForumMessages;
import com.example.Project_Spring.models.IdeasRating;
import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//MODEL

@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", schema = "public")
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Email
    private String email;
    private String password;
    private String name;
    private String lastName;
    private int active;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Set<Messages> sentMessagesList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluator")
    private Set<IdeasRating> listOfIssuedRatings;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient")
    private Set<Messages> receivedMessagesList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Set<ForumMessages> sentForumMessagesList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Set<Topic> sentTopicsList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Set<Messages> sentProposalsList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private Set<Topic> sentSavingsIdeas;

    @Transient
    private String mainRole;


    public UserApp(UserApp userApp) {
        this.id = userApp.getId();
        this.email = userApp.getEmail();
        this.password = userApp.getPassword();
        this.active = userApp.getActive();
        this.roles = userApp.getRoles();
    }
}

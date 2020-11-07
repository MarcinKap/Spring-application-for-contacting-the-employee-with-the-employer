package com.example.Project_Spring.models;


import com.example.Project_Spring.security.UserApp;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ideasRating")
public class IdeasRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_savings_ideas_rating",
            joinColumns =
            @JoinColumn(name = "rating_id"),
            inverseJoinColumns = @JoinColumn(name = "savings_idea_id")
    )
    private SavingsIdeas savingsIdeas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_sent_ratings_by_user",
            joinColumns =
            @JoinColumn(name = "rating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserApp evaluator;





}

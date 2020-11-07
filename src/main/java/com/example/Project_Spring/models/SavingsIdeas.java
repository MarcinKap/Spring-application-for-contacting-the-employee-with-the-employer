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
@Table(name = "savingIdeas")
public class SavingsIdeas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ideaSubject;
    private String description;
    private String benefits;
    private String profitability;
    private String unit;
    private String natureOfTheBenefit;
    private LocalDateTime dateOfCreation;

    private Double averageRating;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "savingsIdeas")
    private Set<IdeasRating> ratingList;



    //    kategoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "savingIdeasAndCategoriesIdList",
            joinColumns =
            @JoinColumn(name = "savingIdea_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private SavingsIdeasCategories savingsIdeasCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "savingIdeasAndWorkAreasIdList",
            joinColumns =
            @JoinColumn(name = "savingIdea_id"),
            inverseJoinColumns = @JoinColumn(name = "workArea_id")
    )
    private WorkAreas workAreas;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "saving_ideas_and_type_of_costs_id_list",
            joinColumns =
            @JoinColumn(name = "saving_idea_id"),
            inverseJoinColumns = @JoinColumn(name = "type_of_costs_id")
    )
    private Set<TypeOfCosts> typeOfCosts;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "list_of_sent_savings_ideas",
            joinColumns =
            @JoinColumn(name = "savings_idea_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserApp sender;





}

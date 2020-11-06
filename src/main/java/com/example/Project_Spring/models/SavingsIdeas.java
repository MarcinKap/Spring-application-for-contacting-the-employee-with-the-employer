package com.example.Project_Spring.models;


import lombok.*;

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
    @JoinTable(name = "savingIdeasAndTypeOfCostsIdList",
            joinColumns =
            @JoinColumn(name = "savingIdea_id"),
            inverseJoinColumns = @JoinColumn(name = "typeOfCosts_id")
    )
    private Set<TypeOfCosts> typeOfCosts;







}

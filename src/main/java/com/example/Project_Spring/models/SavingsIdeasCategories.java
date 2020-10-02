package com.example.Project_Spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "savingIdeasCategories")
public class SavingsIdeasCategories {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;



    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "savingsIdeasCategories")
    private Set<SavingsIdeas> savingsIdeas;




}

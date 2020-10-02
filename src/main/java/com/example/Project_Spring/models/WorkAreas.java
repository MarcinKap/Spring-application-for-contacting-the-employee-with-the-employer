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
@Table(name = "workArea")
public class WorkAreas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workAreas")
    private Set<SavingsIdeas> savingsIdeas;




}

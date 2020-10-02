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
@Table(name = "typeOfCosts")
public class TypeOfCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @JsonIgnore
    @ManyToMany(mappedBy = "typeOfCosts")
    private Set<SavingsIdeas> savingsIdeas;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles")
//    private Set<UserApp> users = new HashSet<>();


}

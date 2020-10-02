package com.example.Project_Spring.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfCostsDto {


    private Long id;
    private String name;
    private Set<SavingsIdeas> savingsIdeas;
}

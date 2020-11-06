package com.example.Project_Spring.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavingsIdeasDto {

    private Long id;
    private String ideaSubject;
    private String description;
    private String benefits;
    private String profitability;
    private String unit;
    private String natureOfTheBenefit;
    private SavingsIdeasCategories savingsIdeasCategories;
    private WorkAreas workAreas;
    private Set<TypeOfCosts> typeOfCosts;
    private LocalDateTime dateOfCreation;
    private Double averageRating;


}

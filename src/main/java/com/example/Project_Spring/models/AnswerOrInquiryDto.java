package com.example.Project_Spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerOrInquiryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text_msg;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answerorinquiry")
    private Set<Question> lista;



}

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
public class QuestionDto {


    private Long id;
    private Long idInquiry;


    //    AnswerOrInquiry inquiry;
//    List<AnswerOrInquiry> answers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Survey> lista;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "question_answer",
            joinColumns =
            @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private AnswerOrInquiry answer;



}

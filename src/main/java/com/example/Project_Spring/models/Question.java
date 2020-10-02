package com.example.Project_Spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_inquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "question_answer",
            joinColumns =
            @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private AnswerOrInquiry answer;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Survey> lista;


}

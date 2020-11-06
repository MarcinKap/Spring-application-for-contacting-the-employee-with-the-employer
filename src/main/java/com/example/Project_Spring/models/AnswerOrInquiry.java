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
@Table(name = "answerorinquiry")
public class AnswerOrInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String textMsg;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answer")
    private Set<Question> lista;

}

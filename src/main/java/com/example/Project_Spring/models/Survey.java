package com.example.Project_Spring.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "survey")
public class Survey {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa_ankiety;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "survey_question",
            joinColumns =
            @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id")
    )
    private Question question;

//    public Survey(Survey survey) {
//        this.id = survey.getId();
//        this.lista = survey.getLista();
//        this.nazwa_ankiety = survey.getNazwa_ankiety();
//
//    }


}

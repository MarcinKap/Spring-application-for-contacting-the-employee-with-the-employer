package com.example.Project_Spring.repositories;


import com.example.Project_Spring.models.IdeasRating;
import com.example.Project_Spring.models.SavingsIdeas;
import com.example.Project_Spring.security.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeasRatingRepository extends JpaRepository<IdeasRating, Long> {

    @Query("select i from IdeasRating i where (i.id = ?1) ")
    IdeasRating findIdeasRatingById(Long ideaId);

    @Query("select i from IdeasRating i where (i.evaluator = ?1 AND i.savingsIdeas = ?2) ")
    IdeasRating findIdeasRatingByUserAndSavingsIdeas(UserApp userApp, SavingsIdeas savingsIdeas);

//    @Query("select avg(rating) from IdeasRating i where (i.ideaId= ?1)")
//    Double findAverageRating(Long ideaId);


}

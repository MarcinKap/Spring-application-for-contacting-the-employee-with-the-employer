package com.example.Project_Spring.repositories;


import com.example.Project_Spring.models.IdeasRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeasRatingRepository extends JpaRepository<IdeasRating, Long> {

    @Query("select i from IdeasRating i where" + "(i.ideaId= ?2) AND  i.userId = ?1")
    IdeasRating findIdeasRatingByUserIdAndAndIdeaId(Integer userId, Long ideaId);


    @Query("select avg(rating) from IdeasRating i where (i.ideaId= ?1)")
    Double findAverageRating(Long ideaId);



}

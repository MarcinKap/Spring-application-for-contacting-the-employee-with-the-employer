package com.example.Project_Spring.repositories;


import com.example.Project_Spring.models.SavingsIdeasCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsIdeasCategoriesRepository extends JpaRepository<SavingsIdeasCategories, Long> {



    @Query("select s from SavingsIdeasCategories s where s.id = ?1")
    SavingsIdeasCategories findIdeaCategoryById(Long id);


}

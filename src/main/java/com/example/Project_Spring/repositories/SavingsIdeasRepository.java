package com.example.Project_Spring.repositories;


import com.example.Project_Spring.models.SavingsIdeas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsIdeasRepository extends JpaRepository<SavingsIdeas, Long> {




}

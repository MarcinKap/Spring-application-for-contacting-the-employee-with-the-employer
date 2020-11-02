package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.News;
import com.example.Project_Spring.models.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {




}

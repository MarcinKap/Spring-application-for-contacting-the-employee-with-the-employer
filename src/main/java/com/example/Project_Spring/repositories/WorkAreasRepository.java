package com.example.Project_Spring.repositories;


import com.example.Project_Spring.models.WorkAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkAreasRepository extends JpaRepository<WorkAreas, Long> {




    @Query("select w from WorkAreas w where w.id = ?1")
    WorkAreas findWorkAreasById(Long id);


}

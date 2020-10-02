package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.TypeOfCosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfCostsRepository extends JpaRepository<TypeOfCosts, Long> {


    @Query("select t from TypeOfCosts t where t.id = ?1")
    TypeOfCosts findTypeOfCostsBy(Long id);


}



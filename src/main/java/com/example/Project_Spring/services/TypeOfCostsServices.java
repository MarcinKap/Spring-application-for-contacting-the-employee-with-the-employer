package com.example.Project_Spring.services;


import com.example.Project_Spring.models.TypeOfCosts;
import com.example.Project_Spring.repositories.TypeOfCostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeOfCostsServices {

    TypeOfCostsRepository typeOfCostsRepository;


    public List<TypeOfCosts> getAllTypesOfCosts() {
        return typeOfCostsRepository.findAll();
    }

    public TypeOfCosts getTypeOfCostsById(Long id) {
        return typeOfCostsRepository.findTypeOfCostsBy(id);
    }



}

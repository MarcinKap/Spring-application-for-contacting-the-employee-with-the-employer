package com.example.Project_Spring.services;


import com.example.Project_Spring.models.SavingsIdeasCategories;
import com.example.Project_Spring.repositories.SavingsIdeasCategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SavingIdeasCategoriesService {

    SavingsIdeasCategoriesRepository savingsIdeasCategoriesRepository;


    public List<SavingsIdeasCategories> getAllCategories() {
        return savingsIdeasCategoriesRepository.findAll();
    }


    public SavingsIdeasCategories getCategoryById(Long id) {
        return savingsIdeasCategoriesRepository.findIdeaCategoryById(id);
    }





}

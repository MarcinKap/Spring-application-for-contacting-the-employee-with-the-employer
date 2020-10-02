package com.example.Project_Spring.services;


import com.example.Project_Spring.models.WorkAreas;
import com.example.Project_Spring.repositories.WorkAreasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkAreasServices {


    WorkAreasRepository workAreasRepository;


    public List<WorkAreas> getAllWorkAreas() {
        return workAreasRepository.findAll();
    }



    public WorkAreas getWorkAreasById(Long id) {
        return workAreasRepository.findWorkAreasById(id);
    }
}

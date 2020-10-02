package com.example.Project_Spring.mappers;



import com.example.Project_Spring.models.WorkAreas;
import com.example.Project_Spring.models.WorkAreasDto;
import org.springframework.stereotype.Component;

@Component
public class WorkAreaMapper implements Mapper<WorkAreas, WorkAreasDto> {


    @Override
    public WorkAreasDto map(WorkAreas from) {
        return WorkAreasDto
                .builder()
                .id(from.getId())
                .name(from.getName())
                .savingIdeas(from.getSavingsIdeas())
                .build();
    }

    @Override
    public WorkAreas reverseMap(WorkAreasDto to) {
        return WorkAreas
                .builder()
                .id(to.getId())
                .name(to.getName())
                .savingsIdeas(to.getSavingIdeas())
                .build();
    }


}

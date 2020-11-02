package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select substring(n.text,1,3) from News n ")
    public List<News> newsList();


}

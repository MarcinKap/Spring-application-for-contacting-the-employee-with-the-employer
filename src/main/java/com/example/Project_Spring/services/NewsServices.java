package com.example.Project_Spring.services;

import com.example.Project_Spring.mappers.NewsMapper;
import com.example.Project_Spring.models.News;
import com.example.Project_Spring.repositories.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsServices {

    NewsRepository newsRepository;
    NewsMapper newsMapper;



    public void saveNews(News news){
        newsRepository.save(news);
    }


    public Optional<News> findNewsById(Long id){
        return newsRepository.findById(id);
    }

    public List<News> findAllNews(){
//        List<News> newsList = newsRepository.newsList();
//        for (int i = 0; i <newsList.size() ; i++) {
//            System.out.println("costam");
//            System.out.println(newsList.get(i).getText());
//        }


        return newsRepository.findAll();
    }
    public List<News> findAllNewsSortedByDate(){

        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }



}

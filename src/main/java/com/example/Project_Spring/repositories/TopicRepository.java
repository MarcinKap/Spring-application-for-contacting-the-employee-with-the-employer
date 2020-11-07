package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {


    @Query("select t from Topic t where t.topic = ?1")
    Topic findTopicByText(String text);

    @Query("select t from Topic t where t.id = ?1")
    Topic findTopicById(Long id);

//    @Query("select t from Topic t where t.recipientId = ?1 or t.senderId = ?1" )
//    List<Topic> findTopicsByLoggedIdUser(Integer id);

//    @Query("select t from Topic t where (t.recipientId = ?1 or t.senderId = ?1) and (t.forumTopic = ?2 or t.forumTopic = null) " )
//    List<Topic> findTopicsByLoggedIdUserAndForumTopic(Long id, Boolean bool);



//    @Query("select t from Topic t where t.forumTopic = ?1")
//    List<Topic> findTopicsByForumTopicBoolean(Boolean bool);



    @Transactional
    @Modifying
    @Query("delete from Topic t where t.id = ?1")
    int deleteTopicById(Long id);
}

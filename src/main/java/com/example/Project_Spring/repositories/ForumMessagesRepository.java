package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.ForumMessages;
import com.example.Project_Spring.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ForumMessagesRepository extends JpaRepository<ForumMessages, Long> {

    @Transactional
    @Modifying
    @Query("delete from ForumMessages m where m.id = ?1")
    int deleteMessagesById(Long id);

    @Query("select count(id) from ForumMessages m")
    int countNumberOfForumMessages();



}

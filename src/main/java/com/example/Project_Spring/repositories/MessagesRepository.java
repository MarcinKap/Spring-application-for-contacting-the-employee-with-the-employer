package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {


    @Query("select m from Messages m where m.id_recipient = ?1")
    List<Messages> findMessagesByRecipientId(Integer id);

    @Query("select m from Messages m where m.id_sender = ?1")
    List<Messages> findMessagesBySenderId(Integer id);

    @Query("select m from Messages m where m.id_topic = ?1")
    List<Messages> findMessagesByTopicId(Long id);



    @Transactional
    @Modifying
    @Query("delete from Messages m where m.id = ?1")
    int deleteMessagesById(Long id);


}


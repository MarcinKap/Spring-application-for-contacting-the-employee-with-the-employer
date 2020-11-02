package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {


    @Query("select m from Messages m where m.recipientId = ?1")
    List<Messages> findMessagesByRecipientId(Long id);

    @Query("select m from Messages m where m.senderId = ?1")
    List<Messages> findMessagesBySenderId(Long id);

//    @Query("select m from Messages m where m.topicId = ?1")
//    List<Messages> findMessagesByTopicId(Long id);

    @Query("select m from Messages m where (m.recipientId = ?1 and  m.senderId = ?2) or (m.recipientId = ?2 and  m.senderId = ?1)")
    List<Messages> findMessagesByRecipientIdAndSenderId(Long id, Long id2);

    @Transactional
    @Modifying
    @Query("delete from Messages m where m.id = ?1")
    int deleteMessagesById(Long id);

    @Query("select m.senderId from Messages m where m.recipientId = ?1")
    Set<Long> findSenderIdByRecipientId(Long id);

    @Query("select m.recipientId from Messages m where m.senderId = ?1")
    Set<Long> findRecipientIdBySenderId(Long id);

}


package com.example.Project_Spring.repositories;

import com.example.Project_Spring.models.Messages;
import com.example.Project_Spring.security.UserApp;
import org.apache.catalina.User;
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


    @Query("select m from Messages m where (m.recipient = ?1 and  m.sender = ?2) or (m.sender = ?2 and  m.recipient = ?1)")
    List<Messages> findMessagesByRecipientIdAndSenderId(UserApp user1, UserApp user2);

    List<Messages> findMessagesBySender(UserApp userApp);

    @Transactional
    @Modifying
    @Query("delete from Messages m where m.id = ?1")
    int deleteMessagesById(Long id);





//    Po many to one

//@Query("select m.recipientId from Messages m where m.senderId = ?1")
//Set<Long> findRecipientIdBySenderId(Long id);
//    @Query("select m.senderId from Messages m where m.recipientId = ?1")
//    Set<Long> findSenderIdByRecipientId(Long id);
//    @Query("select m from Messages m where (m.recipientId = ?1 and  m.senderId = ?2) or (m.recipientId = ?2 and  m.senderId = ?1)")
//    List<Messages> findMessagesByRecipientIdAndSenderId(Long id, Long id2);
//    @Query("select m from Messages m where m.recipientId = ?1")
//    List<Messages> findMessagesByRecipientId(Long id);
//
//    @Query("select m from Messages m where m.senderId = ?1")
//    List<Messages> findMessagesBySenderId(Long id);

}


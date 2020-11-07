package com.example.Project_Spring.security;

import com.example.Project_Spring.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    @Query(value = "select u from UserApp u where u.email = ?1")
    Optional<UserApp> findUserAppByName(String email);

    @Query(value = "select u from UserApp u where u.email = ?1")
    UserApp findUserAppByEmail(String email);

    @Query(value = "select u from UserApp u where u.id = ?1")
    UserApp findUserAppById(Long id);

//    @Query(value = "select u from UserApp u where u.id = ?1")
//    Set<UserApp> findUsersAppById(Set<Long> idList);

    List<UserApp> findUserAppsByIdIsIn(Set<Long> idList);


    @Query("select u from UserApp u where u.sentMessagesList = ?1")
    List<UserApp> findUserAppsBySentMessagesList(Set<Messages> sentMessagesList);



}

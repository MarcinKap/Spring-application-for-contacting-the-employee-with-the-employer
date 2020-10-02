package com.example.Project_Spring.security.Admin;




import com.example.Project_Spring.security.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<UserApp, Long> {





}

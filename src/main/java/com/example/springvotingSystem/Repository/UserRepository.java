package com.example.springvotingSystem.Repository;

import com.example.springvotingSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email=?1")
    User findByEmail(String email);
    @Query("SELECT COUNT(u) FROM User u")
    long count();
}

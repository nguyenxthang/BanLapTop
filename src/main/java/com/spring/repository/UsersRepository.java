package com.spring.repository;

import com.spring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM Users u WHERE u.username = ?1 AND u.password = ?2")
    Users checkLogin(String username, String password);

}

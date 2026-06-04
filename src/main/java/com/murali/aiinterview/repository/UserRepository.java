package com.murali.aiinterview.repository;

import com.murali.aiinterview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
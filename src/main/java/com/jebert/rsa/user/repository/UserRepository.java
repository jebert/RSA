package com.jebert.rsa.user.repository;

import com.jebert.rsa.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByUserName(String userName);


}

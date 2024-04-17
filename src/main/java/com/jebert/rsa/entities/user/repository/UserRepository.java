package com.jebert.rsa.entities.user.repository;

import com.jebert.rsa.entities.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByUserName(String userName);


}

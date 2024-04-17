package com.jebert.rsa.entities.Clients.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jebert.rsa.entities.Clients.model.Client;

public interface ClientRepository extends JpaRepository<Client, UUID>{
    
}

package com.jebert.rsa.entities.address.repository;

import com.jebert.rsa.entities.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}

package com.jebert.rsa.entities.permission.repository;

import com.jebert.rsa.entities.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {


    Permission findByDescription(String description);
}

package com.jebert.rsa.permission.repository;

import com.jebert.rsa.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {


    Permission findByDescription(String description);
}

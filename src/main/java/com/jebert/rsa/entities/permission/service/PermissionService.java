package com.jebert.rsa.entities.permission.service;

import com.jebert.rsa.entities.permission.model.Permission;
import com.jebert.rsa.entities.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;


import com.jebert.rsa.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository repository;

    public PermissionService() {}

    public List<Permission> findAllPermissions() {
        return repository.findAll();
    }

    public Optional<Permission> findPermissionById (UUID id){
        Optional<Permission> Permission = repository.findById(id);
        return Optional.of(Permission.orElseThrow(()-> new ObjectNotFoundException("Permission not found with id:" + id.toString())));
    }

    public Permission findByDescription(String description){
        var p = repository.findByDescription(description);
        return p;
    }

    @Transactional
    public Permission savePermission (Permission Permission) {
        return repository.save(Permission);
    }

    public void delete(UUID uuid) {
        repository.delete(findPermissionById(uuid).get());
    }

}
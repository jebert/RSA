package com.jebert.rsa.user.service;


import com.jebert.rsa.exceptions.ObjectNotFoundException;
import com.jebert.rsa.permission.service.PermissionService;
import com.jebert.rsa.user.model.User;
import com.jebert.rsa.user.model.helper.UserVo;
import com.jebert.rsa.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;

    @Autowired
    PermissionService permissionService;
    public UserService() {}

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserById (UUID id){
        Optional<User> User = repository.findById(id);
        return Optional.of(User.orElseThrow(()-> new ObjectNotFoundException("User not found with id:" + id.toString())));
    }

    public User findUserByUsername (String username){
        var user = repository.findByUserName(username);
        return user;
    }

    @Transactional
    public User saveUser (User User) {
        return repository.save(User);
    }

    public void delete(UUID uuid) {
        repository.delete(findUserById(uuid).get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found"+ username);
        }

        return user;
    }

    public User convertUserFromVo(@Valid UserVo userVo){
        User user = new User(null, userVo.userName(), userVo.fullName(), passwordEncoder.encode(userVo.password()), userVo.email(), userVo.accountNonExpired(), userVo.accountNonLocked(), userVo.credentialsNonExpired(), userVo.enabled());
        for (String role: userVo.roleDescription()) {
            var permission = permissionService.findBydescription(role);
            if (permission == null) {
                throw new ObjectNotFoundException("Permision " + role + " not found!" );
            }else {
                user.addPermission(permission);
            }

        }return user;
    }
}

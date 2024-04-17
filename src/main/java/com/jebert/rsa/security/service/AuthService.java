package com.jebert.rsa.security.service;
import com.jebert.rsa.entities.user.model.User;
import com.jebert.rsa.entities.user.service.UserService;
import com.jebert.rsa.security.controller.vo.LoginVo;
import com.jebert.rsa.security.controller.vo.SigninVo;
import com.jebert.rsa.security.controller.vo.TokenVo;
import com.jebert.rsa.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    public ResponseEntity login (LoginVo data){
        try {
            var username = data.username();
            var password = data.password();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            var user = userService.findUserByUsername(username);

            TokenVo tokenVo;
            if (user != null) {
                tokenVo = jwtTokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenVo);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    public ResponseEntity signin (SigninVo data){
        try {
            var username = data.username();
            var fullName = data.fullName();
            var email = data.email();
            var password = data.password();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            User userToSave = new User( null, username,fullName, password, email, false, false, false, false);

            var user = userService.saveUser(userToSave);

            TokenVo tokenVo;
            if (user != null) {
                tokenVo = jwtTokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenVo);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }


    public ResponseEntity refreshToken (String username, String refreshToken) {
        var user = userService.findUserByUsername(username);
        var tokenResponse = new TokenVo();

        if (user != null) {
            tokenResponse = jwtTokenProvider.refreshToken(refreshToken);

        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);

    }

}
package com.jebert.rsa;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jebert.rsa.entities.permission.model.Permission;
import com.jebert.rsa.entities.user.model.User;
import com.jebert.rsa.entities.user.service.UserService;

@SpringBootApplication
public class RsaApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(RsaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Permission p = new Permission("ROLE_ADMIN");
	
		UserService service = new UserService();

		User user = new User();
		user.setUserName("jebert");
		user.setFullName("Jonatan Ebert");
		user.setPassword("admin123");
		user.setEmail("jonatan.ebert@gmail.com");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.addPermission(p);

		service.saveUser(user);
	}

}

/*  "userName": "string",
  "fullName": "string",
  "password": "string",
  "email": "string",
  "accountNonExpired": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true,
  "enabled": true,
  "roleDescription": [
    "string"
  ]
} */

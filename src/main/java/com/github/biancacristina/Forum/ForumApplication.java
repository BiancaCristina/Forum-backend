package com.github.biancacristina.Forum;

import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class ForumApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User("José", "jj12", "jose@gmail.com", pe.encode("123"));
		User u2 = new User("Maria", "mm12", "maria@gmail.com", pe.encode("456"));

		userRepository.saveAll(Arrays.asList(u1,u2));
	}
}

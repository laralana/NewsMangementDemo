package com.demo.appswave.NewsMangement;

import com.demo.appswave.NewsMangement.entities.Role;
import com.demo.appswave.NewsMangement.entities.User;
import com.demo.appswave.NewsMangement.enumeration.ERole;
import com.demo.appswave.NewsMangement.repository.RoleRepository;
import com.demo.appswave.NewsMangement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class NewsMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsMangementApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Inserting the Roles to the role table
			if (!roleRepository.existsByName(ERole.ROLE_NORMAL)) {
				roleRepository.save(new Role(ERole.ROLE_NORMAL));
			}
			if (!roleRepository.existsByName(ERole.ROLE_ADMIN)) {
				roleRepository.save(new Role(ERole.ROLE_ADMIN));
			}
			if (!roleRepository.existsByName(ERole.ROLE_CONTENT_WRITER)) {
				roleRepository.save(new Role(ERole.ROLE_CONTENT_WRITER));
			}

			// Inserting the admin user
			if (!userRepository.findByEmail("admin@example.com").isPresent()) {
				User adminUser = new User();
				adminUser.setFullName("admin");
				adminUser.setEmail("admin@example.com");
				adminUser.setPassword(passwordEncoder.encode("admin"));
				adminUser.setRoles(Set.of(roleRepository.findByName(ERole.ROLE_ADMIN).get()));
				userRepository.save(adminUser);
			}
		};
	}

}

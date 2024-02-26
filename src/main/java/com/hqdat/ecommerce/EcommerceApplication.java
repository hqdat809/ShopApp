package com.hqdat.ecommerce;

import com.hqdat.ecommerce.model.Role;
//import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.RoleRepository;
import com.hqdat.ecommerce.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Ecommerce Project",
				version = "1.0.0"
		)
)
public class EcommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailsServices(RoleRepository roleRepository,  PasswordEncoder passwordEncoder) {
////			Role adminRole = Role.builder().name("ROLE_ADMIN").build();
////			Role savedRole = roleRepository.save(adminRole);
////
////		UserDetails admin = User.builder()
////					.fullName("ADMIN")
////					.role(savedRole)
////					.email("admin")
////					.password(passwordEncoder.encode("admin"))
////					.build();
//
//		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//		ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//
//		SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("USER");
//		SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("ADMIN");
//
//		roles.add(role1);
//		roles.add(role2);
//
//		User anshulUser = new User("hqdat", "123", roles);
//
//		// Method 2 - Creating username, password, and role
//		// UserDetails anshulUser = User.withUsername("Anshul").password("123").roles("USER", "ADMIN").build();
//
//		userDetailsManager.createUser(anshulUser);
//		return userDetailsManager;
//	}

//			Add first user account when start app
    @Bean
	CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
			Optional<User> user = userRepository.findByEmail("hqdat0809@gmail.com");
			if(user.isEmpty()) {
				Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
				if(userRole.isEmpty()) {
					Role createdUserRole = Role.builder().name("ROLE_USER").build();
					Role savedRole = roleRepository.save(createdUserRole);

					User newUser = User.builder()
							.email("hqdat0809@gmail.com")
							.address("Tay Son")
							.fullName("Ha Quoc Dat")
							.password(passwordEncoder.encode("0392338494"))
							.phoneNumber("0392338494")
							.role(savedRole)
							.build();
					User savedUser = userRepository.save(newUser);
				} else {
					User newUser = User.builder()
							.email("hqdat0809@gmail.com")
							.address("Tay Son")
							.fullName("Ha Quoc Dat")
							.password(passwordEncoder.encode("0392338494"))
							.phoneNumber("0392338494")
							.role(userRole.get())
							.build();

					User savedUser = userRepository.save(newUser);
				}
			}

//			Add first admin account when start app
			Optional<User> admin = userRepository.findByEmail("hqdat08092001@gmail.com");
			if(admin.isEmpty()) {
				Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
				if(adminRole.isEmpty()) {
					Role createdAdminRole = Role.builder().name("ROLE_ADMIN").build();
					Role savedRole = roleRepository.save(createdAdminRole);

					User newAdmin = User.builder()
							.email("hqdat08092001@gmail.com")
							.address("Tay Son")
							.fullName("Ha Quoc Dat")
							.password(passwordEncoder.encode("0392338494"))
							.phoneNumber("0392338494")
							.role(savedRole)
							.build();
					User savedUser = userRepository.save(newAdmin);
				} else {
					User newAdmin = User.builder()
							.email("hqdat08092001@gmail.com")
							.address("Tay Son")
							.fullName("Ha Quoc Dat")
							.password(passwordEncoder.encode("0392338494"))
							.phoneNumber("0392338494")
							.role(adminRole.get())
							.build();

					User savedUser = userRepository.save(newAdmin);
				}

			}
        };
    }
}

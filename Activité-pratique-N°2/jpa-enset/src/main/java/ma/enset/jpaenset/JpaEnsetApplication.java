package ma.enset.jpaenset;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.repositories.UserRepository;
import ma.enset.jpaenset.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaEnsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEnsetApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User user1 = new User();
            user1.setUsername("User1");
            user1.setPassword("1234");
            userService.addUser(user1);

            User user2 = new User();
            user2.setUsername("admin");
            user2.setPassword("1234");
            userService.addUser(user2);

            Role role = new Role();
            role.setRoleName("Student");
            userService.addNewRole(role);

            Role role1 = new Role();
            role1.setRoleName("User");
            userService.addNewRole(role1);

            Role role2 = new Role();
            role2.setRoleName("Admin");
            userService.addNewRole(role2);

            userService.addRoleToUser("User1","Student");
            userService.addRoleToUser("User1","User");
            userService.addRoleToUser("admin","Admin");

            try {
                User user = userService.authentification("User1", "1234");
                System.out.println(user.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}

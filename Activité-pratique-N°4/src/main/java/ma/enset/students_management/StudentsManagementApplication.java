package ma.enset.students_management;

import ma.enset.students_management.Entities.Genre;
import ma.enset.students_management.Entities.Student;
import ma.enset.students_management.repositories.StudentRepository;
import ma.enset.students_management.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class StudentsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsManagementApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args ->{
            studentRepository.save(new Student(null, "khalil", "khalil", "khalil.kha@gmail.com", new Date(), Genre.MASCULIN, false));
            studentRepository.save(new Student(null, "saloua", "saloua", "saloua@gmail.com", new Date(), Genre.FEMININ, true));
            studentRepository.save(new Student(null, "ayoub", "ayoub", "ayoub@gmail.com", new Date(), Genre.MASCULIN, true));
            studentRepository.save(new Student(null, "sara", "sara", "sara@gmail.com", new Date(), Genre.FEMININ, false));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner saveUsers (SecurityService securityService) {
        return args -> {
            securityService.saveNewUser( "admin", "admin", "admin");
            securityService.saveNewUser( "user1", "user", "user");

            securityService.saveNewRole("ADMIN", "This is the admin role !");
            securityService.saveNewRole("USER", "This is the user role !");

            securityService.addRoleToUser( "admin", "ADMIN");
            securityService.addRoleToUser( "admin", "USER");
            securityService.addRoleToUser( "user1", "USER");
        };
    }
}

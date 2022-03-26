package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            //patientRepository.save(new Patient(null, "Hassan", new Date(), false, 12));
            //patientRepository.save(new Patient(null, "Khalil", new Date(), true, 20));
            //patientRepository.save(new Patient(null, "Yassine", new Date(), true, 2));
            //patientRepository.save(new Patient(null, "Ayoub", new Date(), false, 22));

           // patientRepository.findAll().forEach(patient -> {
              //  System.out.println(patient.getNom());
            //});

        };
    }
}

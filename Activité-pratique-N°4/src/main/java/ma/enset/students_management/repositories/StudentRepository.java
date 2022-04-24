package ma.enset.students_management.repositories;

import ma.enset.students_management.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findByNomContains(String nom, Pageable pageable);
}


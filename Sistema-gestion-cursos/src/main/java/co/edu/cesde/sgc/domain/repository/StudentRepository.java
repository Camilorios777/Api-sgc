package co.edu.cesde.sgc.domain.repository;

import co.edu.cesde.sgc.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);

    Optional<Student> findById(Long id);

    List<Student> findAll();

    Student update(Student student);

    void deleteById(Long id);

    boolean existsById(Long id);
}

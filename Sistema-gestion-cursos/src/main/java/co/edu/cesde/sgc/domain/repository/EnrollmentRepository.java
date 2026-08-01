package co.edu.cesde.sgc.domain.repository;

import co.edu.cesde.sgc.domain.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {

    Enrollment save(Enrollment enrollment);

    Optional<Enrollment> findById(Long id);

    List<Enrollment> findAll();

    List<Enrollment> findByCourseId(Long courseId);

    List<Enrollment> findByStudentId(Long studentId);

    Enrollment update(Enrollment enrollment);

    void deleteById(Long id);

    boolean existsById(Long id);
}

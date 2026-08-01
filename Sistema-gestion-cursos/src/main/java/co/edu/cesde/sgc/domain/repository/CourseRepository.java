package co.edu.cesde.sgc.domain.repository;

import co.edu.cesde.sgc.domain.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    Course save(Course course);

    Optional<Course> findById(Long id);

    List<Course> findAll();

    Course update(Course course);

    void deleteById(Long id);

    boolean existsById(Long id);
}

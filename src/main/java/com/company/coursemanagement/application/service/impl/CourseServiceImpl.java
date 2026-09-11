package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.CourseDTO;
import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO create(CourseDTO dto) {
        validateCourse(dto);
        Course course = new Course(null, dto.getCode(), dto.getName(), dto.getDescription(), dto.getMaxCapacity());
        return toDTO(courseRepository.save(course));
    }

    @Override
    public CourseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return toDTO(course);
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public CourseDTO update(Long id, CourseDTO dto) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        validateCourse(dto);
        Course course = new Course(id, dto.getCode(), dto.getName(), dto.getDescription(), dto.getMaxCapacity());
        return toDTO(courseRepository.save(course));
    }

    @Override
    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }

    private void validateCourse(CourseDTO dto) {
        if (dto.getCode() == null || dto.getCode().isBlank()) {
            throw new BusinessException("El codigo del curso es obligatorio");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new BusinessException("El nombre del curso es obligatorio");
        }
        if (dto.getMaxCapacity() == null || dto.getMaxCapacity() < 1) {
            throw new BusinessException("La capacidad maxima debe ser al menos 1");
        }
    }

    private CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getCode(), course.getName(),
                course.getDescription(), course.getMaxCapacity());
    }
}
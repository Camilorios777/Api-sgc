package co.edu.cesde.sgc.application.service.impl;

import co.edu.cesde.sgc.application.dto.CourseDTO;
import co.edu.cesde.sgc.application.dto.CourseMapper;
import co.edu.cesde.sgc.application.service.CourseService;
import co.edu.cesde.sgc.domain.exception.CourseNotFoundException;
import co.edu.cesde.sgc.domain.model.Course;
import co.edu.cesde.sgc.domain.repository.CourseRepository;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO create(CourseDTO courseDTO) {
        Course course = CourseMapper.toModel(courseDTO);
        course.setId(null);
        Course saved = courseRepository.save(course);
        return CourseMapper.toDTO(saved);
    }

    @Override
    public CourseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return CourseMapper.toDTO(course);
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDTO)
                .toList();
    }

    @Override
    public CourseDTO update(Long id, CourseDTO courseDTO) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        Course course = CourseMapper.toModel(courseDTO);
        course.setId(id);
        Course updated = courseRepository.update(course);
        return CourseMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }
}

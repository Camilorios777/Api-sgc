package co.edu.cesde.sgc.application.service;

import co.edu.cesde.sgc.application.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    CourseDTO create(CourseDTO courseDTO);

    CourseDTO findById(Long id);

    List<CourseDTO> findAll();

    CourseDTO update(Long id, CourseDTO courseDTO);

    void deleteById(Long id);
}

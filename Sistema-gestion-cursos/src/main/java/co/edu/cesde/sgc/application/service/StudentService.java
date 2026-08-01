package co.edu.cesde.sgc.application.service;

import co.edu.cesde.sgc.application.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO create(StudentDTO studentDTO);

    StudentDTO findById(Long id);

    List<StudentDTO> findAll();

    StudentDTO update(Long id, StudentDTO studentDTO);

    void deleteById(Long id);
}

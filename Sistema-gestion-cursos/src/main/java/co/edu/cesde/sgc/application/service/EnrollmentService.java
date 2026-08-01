package co.edu.cesde.sgc.application.service;

import co.edu.cesde.sgc.application.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {

    EnrollmentDTO create(EnrollmentDTO enrollmentDTO);

    EnrollmentDTO findById(Long id);

    List<EnrollmentDTO> findAll();

    EnrollmentDTO cancel(Long id);

    void deleteById(Long id);
}

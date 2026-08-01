package co.edu.cesde.sgc.application.dto;

import co.edu.cesde.sgc.domain.model.EnrollmentStatus;

import java.time.LocalDate;

public record EnrollmentDTO(
        Long id,
        Long studentId,
        Long courseId,
        LocalDate enrollmentDate,
        EnrollmentStatus status
) {
}

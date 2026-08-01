package co.edu.cesde.sgc.application.dto;

import co.edu.cesde.sgc.domain.model.Enrollment;

public class EnrollmentMapper {

    private EnrollmentMapper() {
    }

    public static EnrollmentDTO toDTO(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );
    }

    public static Enrollment toModel(EnrollmentDTO dto) {
        return new Enrollment(
                dto.id(),
                dto.studentId(),
                dto.courseId(),
                dto.enrollmentDate(),
                dto.status()
        );
    }
}

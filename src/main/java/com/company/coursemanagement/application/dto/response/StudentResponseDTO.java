package com.company.coursemanagement.application.dto.response;

import java.time.LocalDate;
import com.company.coursemanagement.domain.model.Student;

public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate
) {
    public static StudentResponseDTO from(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate()
        );
    }
}
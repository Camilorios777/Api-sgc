package co.edu.cesde.sgc.application.dto;

import co.edu.cesde.sgc.domain.model.Student;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentDTO toDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate()
        );
    }

    public static Student toModel(StudentDTO dto) {
        return new Student(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.birthDate()
        );
    }
}

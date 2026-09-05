package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO create(StudentDTO dto) {
        validateStudent(dto);
        Student student = new Student(null, dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getBirthDate());
        return toDTO(studentRepository.save(student));
    }

    @Override
    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return toDTO(student);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        validateStudent(dto);
        Student student = new Student(id, dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getBirthDate());
        return toDTO(studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    private void validateStudent(StudentDTO dto) {
        if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
            throw new BusinessException("El nombre es obligatorio");
        }
        if (dto.getLastName() == null || dto.getLastName().isBlank()) {
            throw new BusinessException("El apellido es obligatorio");
        }
        if (dto.getEmail() == null || !dto.getEmail().contains("@")) {
            throw new BusinessException("El email no tiene un formato valido");
        }
        if (dto.getBirthDate() == null || dto.getBirthDate().isAfter(LocalDate.now())) {
            throw new BusinessException("La fecha de nacimiento no puede ser futura");
        }
    }

    private StudentDTO toDTO(Student student) {
        return new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getBirthDate());
    }
}
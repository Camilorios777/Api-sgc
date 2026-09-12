package com.company.coursemanagement.presentation.controller;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.presentation.exception.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.company.coursemanagement.application.dto.CreateStudentDTO;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateStudentDTO dto) {
        try {
            var studentDTO = new StudentDTO(null, dto.firstName(), dto.lastName(), dto.email(), dto.birthDate());
            var created = studentService.create(studentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            var student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        var students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody StudentDTO dto) {
        try {
            var updated = studentService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            studentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        }
    }
}
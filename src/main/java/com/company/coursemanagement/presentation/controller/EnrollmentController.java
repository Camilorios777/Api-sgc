package com.company.coursemanagement.presentation.controller;

import com.company.coursemanagement.application.dto.EnrollmentDTO;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.exception.EnrollmentNotFoundException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.presentation.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Object> enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        try {
            var created = enrollmentService.enrollStudent(studentId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (StudentNotFoundException | CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            var enrollment = enrollmentService.findById(id);
            return ResponseEntity.ok(enrollment);
        } catch (EnrollmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        var enrollments = enrollmentService.findAll();
        return ResponseEntity.ok(enrollments);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Object> cancel(@PathVariable Long id) {
        try {
            enrollmentService.cancelEnrollment(id);
            return ResponseEntity.noContent().build();
        } catch (EnrollmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        }
    }
}
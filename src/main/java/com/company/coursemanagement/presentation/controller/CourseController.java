package com.company.coursemanagement.presentation.controller;

import com.company.coursemanagement.application.dto.CourseDTO;
import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.presentation.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CourseDTO dto) {
        try {
            var created = courseService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            var course = courseService.findById(id);
            return ResponseEntity.ok(course);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        var courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CourseDTO dto) {
        try {
            var updated = courseService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(e.getMessage()));
        }
    }
}
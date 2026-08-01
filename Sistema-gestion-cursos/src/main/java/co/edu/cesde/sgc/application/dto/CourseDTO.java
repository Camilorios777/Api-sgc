package co.edu.cesde.sgc.application.dto;

public record CourseDTO(
        Long id,
        String code,
        String name,
        String description,
        Integer maxCapacity
) {
}

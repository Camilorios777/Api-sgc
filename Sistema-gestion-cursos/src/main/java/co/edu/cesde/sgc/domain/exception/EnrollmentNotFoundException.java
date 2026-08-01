package co.edu.cesde.sgc.domain.exception;

public class EnrollmentNotFoundException extends BusinessException {

    public EnrollmentNotFoundException(Long id) {
        super("Enrollment not found: " + id);
    }
}

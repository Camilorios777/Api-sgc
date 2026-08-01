package co.edu.cesde.sgc;

import co.edu.cesde.sgc.application.service.CourseService;
import co.edu.cesde.sgc.application.service.EnrollmentService;
import co.edu.cesde.sgc.application.service.StudentService;
import co.edu.cesde.sgc.application.service.impl.CourseServiceImpl;
import co.edu.cesde.sgc.application.service.impl.EnrollmentServiceImpl;
import co.edu.cesde.sgc.application.service.impl.StudentServiceImpl;
import co.edu.cesde.sgc.domain.repository.CourseRepository;
import co.edu.cesde.sgc.domain.repository.EnrollmentRepository;
import co.edu.cesde.sgc.domain.repository.StudentRepository;
import co.edu.cesde.sgc.infrastructure.repository.InMemoryCourseRepository;
import co.edu.cesde.sgc.infrastructure.repository.InMemoryEnrollmentRepository;
import co.edu.cesde.sgc.infrastructure.repository.InMemoryStudentRepository;
import co.edu.cesde.sgc.presentation.CourseMenu;
import co.edu.cesde.sgc.presentation.EnrollmentMenu;
import co.edu.cesde.sgc.presentation.InputReader;
import co.edu.cesde.sgc.presentation.MainMenu;
import co.edu.cesde.sgc.presentation.StudentMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentRepository studentRepository = new InMemoryStudentRepository();
        CourseRepository courseRepository = new InMemoryCourseRepository();
        EnrollmentRepository enrollmentRepository = new InMemoryEnrollmentRepository();


        StudentService studentService = new StudentServiceImpl(studentRepository);
        CourseService courseService = new CourseServiceImpl(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentServiceImpl(
                enrollmentRepository, studentRepository, courseRepository);


        try (Scanner scanner = new Scanner(System.in)) {
            InputReader reader = new InputReader(scanner);

            StudentMenu studentMenu = new StudentMenu(studentService, reader);
            CourseMenu courseMenu = new CourseMenu(courseService, reader);
            EnrollmentMenu enrollmentMenu = new EnrollmentMenu(enrollmentService, reader);

            MainMenu mainMenu = new MainMenu(studentMenu, courseMenu, enrollmentMenu, reader);
            mainMenu.start();
        }
    }
}

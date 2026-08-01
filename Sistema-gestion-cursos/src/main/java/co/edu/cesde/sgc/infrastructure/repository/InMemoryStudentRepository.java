package co.edu.cesde.sgc.infrastructure.repository;

import co.edu.cesde.sgc.domain.model.Student;
import co.edu.cesde.sgc.domain.repository.StudentRepository;
import co.edu.cesde.sgc.shared.IdGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStudentRepository implements StudentRepository {

    private final Map<Long, Student> storage = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator = new IdGenerator();

    @Override
    public Student save(Student student) {
        Long id = idGenerator.next();
        student.setId(id);
        storage.put(id, student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Student> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Student update(Student student) {
        storage.put(student.getId(), student);
        return student;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}

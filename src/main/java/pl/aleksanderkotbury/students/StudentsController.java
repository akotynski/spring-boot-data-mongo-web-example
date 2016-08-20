package pl.aleksanderkotbury.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentsController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") Student student) {
        return student;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        studentRepository.delete(id);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.insert(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        Student updatedStudent = StudentBuilder.fromStudent(student).withId(id).build();
        return studentRepository.save(updatedStudent);
    }

    @GetMapping("/students/search/findByFirstName")
    public Page<Student> findStudentsByFirstName(@RequestParam("firstName") String firstName, Pageable pageable) {
        return studentRepository.findByFirstName(firstName, pageable);
    }
}

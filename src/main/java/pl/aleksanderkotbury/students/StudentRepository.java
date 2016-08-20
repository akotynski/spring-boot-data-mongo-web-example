package pl.aleksanderkotbury.students;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(exported = false)
public interface StudentRepository extends MongoRepository<Student, String> {

   List<Student> findByFirstName(@Param("firstName") String firstName);
}

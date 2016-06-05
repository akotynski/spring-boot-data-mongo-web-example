package pl.aleksanderkotbury;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

   List<Student> findByFirstName(@Param("firstName") String firstName);
}

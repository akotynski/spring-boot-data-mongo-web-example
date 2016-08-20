package pl.aleksanderkotbury.students;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface StudentRepository extends MongoRepository<Student, String> {

    Page<Student> findByFirstName(@Param("firstName") String firstName, Pageable pageable);
}

package pl.aleksanderkotbury;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebIntegrationTest({"server.port:0", "spring.data.mongodb.port: 0"})
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Value("${local.server.port}")
    int tomcatPort;

    @Before
    public void setUp() throws Exception {
        studentRepository.deleteAll();
        RestAssured.port = tomcatPort;
    }

    @Test
    public void shouldFindStudentsByName() {
        // given
        studentRepository.insert(new Student("Jan", "Kowalski", 123456));
        studentRepository.insert(new Student("Jan", "Sobieski", 654321));
        studentRepository.insert(new Student("Anna", "Kowalska", 123654));

        // when
        Response response = when()
                .get("/students/search/findByFirstName?firstName=Jan");

        // then
        response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("_embedded.students.lastName", contains("Kowalski", "Sobieski"))
                .body("_embedded.students.indexNumber", contains(123456, 654321));
    }
}
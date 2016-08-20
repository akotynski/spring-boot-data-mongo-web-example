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
import pl.aleksanderkotbury.students.StudentBuilder;
import pl.aleksanderkotbury.students.StudentRepository;

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
        studentRepository.insert(new StudentBuilder().withId("Jan").withFirstName("Kowalski").withLastName(123456).build());
        studentRepository.insert(new StudentBuilder().withId("Jan").withFirstName("Sobieski").withLastName(654321).build());
        studentRepository.insert(new StudentBuilder().withId("Anna").withFirstName("Kowalska").withLastName(123654).build());

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
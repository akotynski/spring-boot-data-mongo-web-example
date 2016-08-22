package pl.aleksanderkotbury;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.aleksanderkotbury.students.StudentBuilder;
import pl.aleksanderkotbury.students.StudentRepository;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.data.mongodb.port=0")
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @LocalServerPort
    int tomcatPort;

    @Before
    public void setUp() throws Exception {
        studentRepository.deleteAll();
        RestAssured.port = tomcatPort;
    }

    @Test
    public void shouldFindStudentsByName() {
        // given
        studentRepository.insert(new StudentBuilder().withFirstName("Jan").withLastName("Kowalski").withIndexNumber(123456).build());
        studentRepository.insert(new StudentBuilder().withFirstName("Jan").withLastName("Sobieski").withIndexNumber(654321).build());
        studentRepository.insert(new StudentBuilder().withFirstName("Anna").withLastName("Kowalska").withIndexNumber(123654).build());

        // when
        Response response = when()
                .get("/students/search/findByFirstName?firstName=Jan");

        // then
        response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("totalElements", is(2))
                .body("totalPages", is(1))
                .body("first", is(true))
                .body("last", is(true))
                .body("content.lastName", contains("Kowalski", "Sobieski"))
                .body("content.indexNumber", contains(123456, 654321));
    }
}
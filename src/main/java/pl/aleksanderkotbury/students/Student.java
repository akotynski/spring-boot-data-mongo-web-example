package pl.aleksanderkotbury.students;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Student {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private Integer indexNumber;

    @JsonCreator
    public Student(@JsonProperty("id") String id,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("indexNumber") Integer indexNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }
}

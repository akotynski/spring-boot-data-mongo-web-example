package pl.aleksanderkotbury.students;

public class StudentBuilder {
    private String id;
    private String firstName;
    private String lastName;
    private Integer indexNumber;

    public static StudentBuilder fromStudent(Student student) {
        return new StudentBuilder()
                .withId(student.getId())
                .withFirstName(student.getFirstName())
                .withIndexNumber(student.getIndexNumber())
                .withLastName(student.getLastName());
    }

    public StudentBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public StudentBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder withIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
        return this;
    }

    public Student build() {
        return new Student(id, firstName, lastName, indexNumber);
    }
}
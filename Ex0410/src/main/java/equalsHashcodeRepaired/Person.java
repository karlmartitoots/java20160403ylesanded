package equalsHashcodeRepaired;

public class Person {

    private String firstName;
    private String lastName;
    private final int idCode;

    public Person(String firstName, String lastName, int idCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCode = idCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return idCode == person.idCode;
    }

    @Override
    public int hashCode() {
        return idCode;
    }
}

/**
 * Objects of class represent a person.
 *
 * Guarantees to correctly override and implement equals() and hashCode(),
 * using the idCode field as the significant field for object comparison & hashcode generation.
 */
public class Person {
    private final int idCode;
    private final String firstName;
    private final String lastName;

    /**
     * Constructs a new Person object.
     * In the case of any illegal parameters, RuntimeException is thrown.
     *
     * @param idCode Id code of the person. Must be a positive integer.
     * @param firstName First name of the person. Must not be null or empty!
     * @param lastName Last name of the person. Must not be null or empty!
     * @throws RuntimeException in the case of any illegal or null parameters.
     */
    public Person(int idCode, String firstName, String lastName) throws RuntimeException {
        if (idCode < 0) {
            throw new RuntimeException("Person constructor failed: negative id code!");
        }
        this.idCode = idCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getIdCode() {
        return idCode;
    }
}
package equalsHashcode;

/**
 * oop2016ut collections vol2
 * @author Taavi Ilp
 */

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

    /*
    Esiteks peaks equals kindlasti kontrollima ka talle antava
    Objekti o klassi. Kui seda tehakse, siis equals töötab
    seni kuni tekib kaks Person objekti, mille idCode % 4 on
    sama. Siis läheb kood katki.

    Kõige lihtsam oleks panna hashCodeks idCode, eeldades, et
    igal inimesel on see erinev.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Person person = (Person) o;

        return idCode == person.idCode;
    }

    @Override
    public int hashCode() {
        return idCode % 4;
    }
}

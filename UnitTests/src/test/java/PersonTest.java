import org.junit.Assert;
import org.junit.Test;

public class PersonTest {
    @Test
    public void testPersonEquals() {
        Person person1 = new Person(1, "Taavi", "Ilp");
        Person person2 = new Person(1, "Taavi", "Ilp");

        Assert.assertEquals(person1, person2);
    }

    @Test
    public void testGetters() {
        Person person = new Person(1, "Taavi", "Ilp");

        Assert.assertEquals(1, person.getIdCode());
        Assert.assertEquals("Taavi", person.getFirstName());
        Assert.assertEquals("Ilp", person.getLastName());
    }

    @Test(expected=RuntimeException.class)
    public void testConstructorExceptionForNegativeIdCode() {
        Person person = new Person(-1, "Taavi", "Ilp");
    }
}
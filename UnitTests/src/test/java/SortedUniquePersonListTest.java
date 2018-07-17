import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedUniquePersonListTest {

    @Test
    public void testAddToList() {
        SortedUniquePersonList list = new SortedUniquePersonListImpl();
        list.add(new Person(1, "Taavi", "Ilp"));

        assertEquals(1, list.size());

        list.add(new Person(2, "M�rt", "Bakhoff"));

        assertEquals(2, list.size());
    }

    @Test
    public void testGetElementAtInside(){
        SortedUniquePersonList list = new SortedUniquePersonListImpl();

        Person person1 = new Person(1, "First1", "Last");
        Person person2 = new Person(2, "First2", "Last");
        Person person3 = new Person(3, "First3", "Last");

        assertEquals(person1, list.getElementAt(0));
        assertEquals(person2, list.getElementAt(1));
        assertEquals(person3, list.getElementAt(2));
    }

    @Test
    public void testGetElementAtOutside(){
        SortedUniquePersonList list = new SortedUniquePersonListImpl();

        Person person1 = new Person(1, "First1", "Last");
        list.add(person1);

        assertEquals(null, list.getElementAt(-1));
    }

    @Test
    public void testSize(){
        SortedUniquePersonList list = new SortedUniquePersonListImpl();

        Person person1 = new Person(1, "First1", "Last");
        Person person2 = new Person(2, "First2", "Last");
        Person person3 = new Person(3, "First3", "Last");
        Person person4 = new Person(4, "First4", "Last");
        Person person5 = new Person(5, "First5", "Last");
        Person person6 = new Person(6, "First6", "Last");

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);

        assertTrue(list.size() <= 12 && list.size() >= 6);

        list.removeElement(1);
        list.removeElement(2);
        list.removeElement(3);
        list.removeElement(4);

        assertTrue(list.size() <= 4 && list.size() >=2);
    }

    @Test
    public void testTndexOfByCodeInside(){
        SortedUniquePersonList list = new SortedUniquePersonListImpl();

        Person person4 = new Person(4, "First4", "Last");
        Person person1 = new Person(1, "First1", "Last");
        Person person5 = new Person(5, "First5", "Last");
        Person person2 = new Person(2, "First2", "Last");
        Person person6 = new Person(6, "First6", "Last");
        Person person3 = new Person(3, "First3", "Last");

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);

        assertEquals(0, list.indexOfByCode(1));
        assertEquals(5, list.indexOfByCode(6));
    }

    @Test
    public void testTndexOfByCodeOutside(){
        SortedUniquePersonList list = new SortedUniquePersonListImpl();

        Person person1 = new Person(1, "First1", "Last");
        Person person2 = new Person(2, "First2", "Last");
        Person person3 = new Person(3, "First3", "Last");
        Person person4 = new Person(4, "First4", "Last");
        Person person5 = new Person(5, "First5", "Last");
        Person person6 = new Person(6, "First6", "Last");

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);

        assertEquals(-1, list.indexOfByCode(7));
        assertEquals(-1, list.indexOfByCode(-1));

    }

    @Test
    public void testTndexOfByNamesInside(){
        SortedUniquePersonList list = new SortedUniquePersonListImpl();

        Person person1 = new Person(1, "First1", "Last");
        Person person2 = new Person(2, "First2", "Last");
        Person person3 = new Person(3, "First3", "Last");
        Person person4 = new Person(4, "First4", "Last");
        Person person5 = new Person(5, "First5", "Last");
        Person person6 = new Person(6, "First6", "Last");

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);

        assertEquals(2, list.indexOfByNames("First3", "Last"));
        assertEquals(5, list.indexOfByNames("First6", "Last"));

    }

    @Test
    public void testTndexOfByNamesOutside(){
        SortedUniquePersonList list = new SortedUniquePersonListImpl();

        Person person1 = new Person(1, "First1", "Last");
        Person person2 = new Person(2, "First2", "Last");
        Person person3 = new Person(3, "First3", "Last");
        Person person4 = new Person(4, "First4", "Last");
        Person person5 = new Person(5, "First5", "Last");
        Person person6 = new Person(6, "First6", "Last");

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);

        assertEquals(-1, list.indexOfByNames("Heiti", "Ehrpais"));
        assertEquals(-1, list.indexOfByNames("Aleksis", "Zalitis"));

    }

}

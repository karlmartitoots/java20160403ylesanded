import java.util.ArrayList;
import java.util.List;

public class SortedUniquePersonListImpl implements SortedUniquePersonList {

    private final List<Person> list = new ArrayList<>();

    @Override
    public Person getElementAt(int index) {
        if (list.size() >= index) {
            return null;
        }
        return list.get(index);
    }

    @Override
    public int indexOfByCode(int idCode) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdCode() == idCode) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int indexOfByNames(String firstName, String lastName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirstName().equals(firstName) || list.get(i).getLastName().equals(lastName)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean add(Person person) {
        if (list.size() == 0) {
            list.add(person);
            return true;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdCode() == person.getIdCode()) {
                return false;
            }
            if (list.get(i).getIdCode() < person.getIdCode()) {
                list.add(i, person);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeElement(int idCode) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdCode() == idCode) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean contains(Person person) {
        for (Person existing : list) {
            if (person.equals(existing)) {
                return true;
            }
        }

        return false;
    }
}
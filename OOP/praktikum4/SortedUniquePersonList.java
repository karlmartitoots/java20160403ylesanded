/**
 * Collection for holding Person objects.<br>
 * Provides the following guarantees:<br>
 * 1) Elements are guaranteed to be in ascending order, sorted by their ID code value.<br>
 * 2) Elements are guaranteed to have unique ID code values.<br>
 *
 * Uses an underlying array for storing the elements. <br>
 * The object guarantees to not use more than twice the required array size.<br>
 * For example, if currently 10 persons are stored, then the underlying array size might range from 10 to 20, but will not be larger.
 */
public class SortedUniquePersonList {

    private Person[] personList;


    /**
     * Returns reference to object at the given index. Checks that the given index is in bounds of the underlying array, returns null if it isn't.
     * @param index Index at which the object is searched.
     * @return Person object at the given index, or null if the index is out of bounds.
     */
    public Person getElementAt(int index) {
        if(Person[].length() > index){
            return null;
        } else{
            return Person[index];
        }
    }

    /**
     * Returns the index of the object with the given ID code. If an object with the given ID code is not present, returns -1.
     * @param idCode ID code that is searched.
     * @return Index at which the Person object with the given ID code can be found, or -1 if no such ID code is present.
     */
    public int indexOf(int idCode) {

    }

    /**
     * Attempts to add the person to the collection, but only if no person with the same ID code is already present.<br>
     * If an element is added, it is inserted to the correct position according to their ID code. Also, the index of all subsequent elements is then increased.<br>
     * If a Person object with the same ID code is already present, does nothing.
     * @param person Person object to be added.
     * @return true if person was added to the collection, false otherwise.
     */
    public boolean add(Person person) {
        if(){
            Person person = new Person();
        }
    }

    /**
     * Attempts to remove the person with the given ID code from the collection. Does nothing if no Person object with the given ID code is present.<br>
     * In the case of a successful removal of an object, decreases the index of all subsequent elements.
     * @param idCode ID code that is searched.
     * @return true if the person with the given ID code was removed, false otherwise.
     */
    public boolean removeElement(int idCode) {

    }

    /**
     * Calculates and returns the size of the collection.
     * @return Number of elements in the collection.
     */
    public int size() {

    }
}

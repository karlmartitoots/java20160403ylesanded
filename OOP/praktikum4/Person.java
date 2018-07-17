public class Person {

    private final int idCode;
    private final String firstName;
    private final String lastName;

    Person(String initialFirstName, String initialLastName){
        this.firstName = initialFirstName;
        this.lastName = initialLastName;
    }

    public getFirstName(){
        return this.firstName;
    }

    public getLastName(){
        return this.lastName;
    }

}
package equalsHashcodeRepaired;

public class Newspaper {

    private final String name;
    private final int yearOfFounding;
    private final int registrationCode;

    public Newspaper(String name, int yearOfFounding, int registrationCode) {
        this.name = name;
        this.registrationCode = registrationCode;
        this.yearOfFounding = yearOfFounding;
    }

    public int getRegistrationCode() {
        return registrationCode;
    }

    public int getYearOfFounding() {
        return yearOfFounding;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Newspaper newspaper = (Newspaper) o;

        return registrationCode == newspaper.registrationCode;
    }

    @Override
    public int hashCode() {
        return registrationCode;
    }
}

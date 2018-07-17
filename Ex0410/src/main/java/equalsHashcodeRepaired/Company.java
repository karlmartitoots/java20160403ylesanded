package equalsHashcodeRepaired;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private List<String> customerNames = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCustomer(String name) {
        customerNames.add(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return name.equals(company.name) && customerNames.size() == company.customerNames.size();
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = 31 * result + customerNames.size();
        return result;
    }
}

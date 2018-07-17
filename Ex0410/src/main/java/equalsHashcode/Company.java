package equalsHashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * oop2016ut collections vol2
 * @author Taavi Ilp
 */

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

    /*
        Selline equals/hashcode implementatsioon töötaks kuid
        läheks katki, kui luuakse kaks Companyt, millel on sama
        nimi, kuid erinevad kliendid.

        Võimalusi hashcode paremaks tööks:
        *Võtta arvesse customerNames olevate Stringide arv.
            kui Company.name on kahel Company objectil sama,
            aga Company.customerNames.size() on erivev, suudab
            HashMap neid võtmeid eristada.
        *Võtta arvesse kõik customerName liikmed.
            selline implementatsioon teeks võimatuks, et kahel
            sama Company.name fieldiga, kuid erinevate klientidega
            Company objekt pannakse HashMapis sama võtme alla.
            Ilmselgelt on see ka kõige mälunõudlikum ja tuleks
            selle implementeerimise asemel pigem Company.name
            valida erinev.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

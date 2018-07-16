package equalsHashcode;

/**
 * oop2016ut collections vol2
 * @author Taavi Ilp
 */


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

    /*
    See equals töötab hästi. Võimalik ka, et töötab isegi
    liiga hästi ja saaks optimiseerida:

    Oletame, et mõnele uuele Newspaper objektile pannakse
    välja registrationCode väärtuseks sama int, mis eelnevalt
    mõnel teisel Newspaper objektil oli. Kui keegi otsustaks mingil
    põhjusel nii teha, siis siin likvideeritakse võimalus, et
    nende võtmed sassi lähevad sellega, et kontrollitakse ka nende
    name väljasid. Juba registrationCode võiks igal uuel
    registreeritaval Newspaper objektil olla erinev, muidu
    sellel väljal poleks ilmselt mingit mõtet. Tõepoolest, siin
    kontrollitakse mõlemad name ja registrationCode läbi ja tehakse
    kood eriti lollikindlaks, kuid pigem võiks registrationCode alati
    erinev olla ja ainult selle põhjal hashcode moodustada.

    Meetodis hashCode pole vaja ilmselt arvesse võtta yearOfFounding
    välja.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Newspaper newspaper = (Newspaper) o;

        return registrationCode == newspaper.registrationCode && (name == null ? newspaper.name == null : name.equals(newspaper.name));
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + yearOfFounding;
        result = 31 * result + registrationCode;
        return result;
    }
}

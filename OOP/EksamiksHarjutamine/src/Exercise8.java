import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Exercise8 {
}

//    Käsurea argumentidena anti programmile ette 1 ja 2 ning loodeti saada ekraanile 0.5. Paraku see
//        päriselt ei õnnestunud. Järgnevas loetelus on erinevad võimalikud põhjused. Selgitada iga variandi
//        korral, kuivõrd see antud juhul aktuaalne on. Võib eeldada, et vajalikud asjad on imporditud.
//        1. Avalik meetod ei saa privaatset meetodit välja kutsuda.
/*
    Avalik meetod saab kindlasti privaatse meetodi, mis asub samas klassis, välja kutsuda.
    Privaatset meetodit saab ainult välja kutsuda samas klassis.
 */
//        2. Antud argumentide korral ei tagasta funktsioon f1 arvu 0.5.
/*
Kuna toimub täisavude jagamine, sis tuleb väärtuseks 0.0
 */
//        3. Peameetodi päises puudub throws ArithmeticException.
/*
Kuna exceptionit tegelikult ei visatagi siis see ei mängi siin mingit rolli.
 */
//        4. Tekib ArrayIndexOutOfBoundsException erind.
/*
Kuna programm saab kaks muutujat ning üritatakse lugeda esimesel ja teisel positsioonil asuvaid muutujaid,
siis AIOOBE-d ei teki
 */
//        5. Tekib ArithmeticException erind.
/*
ArithmeticExceptionit ei teki, sest täisarvude nii jagamine on javas täiesti võimalik.
 */
//        6. Väljund kirjutatakse hoopis faili.
/*
Väljund kirjutatakse tõepoolest faili.
 */
//        Mis on teisiti, kui käsurea argumentidena antakse programmile ette 1 ja 0?
/*
Tekib Arithmeticexception. Main viskab selle.
 */
//        Mis on teisiti, kui käsurea argumente üldse ette ei anta?
/*
Tekib AIOOBE. Main viskab selle.
 */
class Aritmeetika {
    public static void main(String[] args) throws FileNotFoundException {
        OutputStream output = new FileOutputStream("systemout.txt");
        PrintStream printOut = new PrintStream(output);
        System.setOut(printOut);
        System.out.println(f1(Integer.parseInt(args[0]),
                Integer.parseInt(args[1])));
    }
    private static double f1(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
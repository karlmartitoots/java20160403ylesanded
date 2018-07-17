import java.util.ArrayList;

/**
 * Created by Karl on 18.02.2016.
 */
public class IDkaart {

    private String IDcode;
    private String password;
    private ArrayList<String> prevPasswords;

    IDkaart(String initIDcode, String initPassword){
        IDcode = initIDcode;
        password = initPassword;
        prevPasswords.add(password);
    }
    //39512064232

    public String getIDcode(){
        return this.IDcode;
    }

    public String getPassword(){
        return this.password;
    }

    public void setIDcode(String newIDcode){
        this.IDcode = newIDcode;
    }

    public boolean setPassword(String newPassword){
        if(prevPasswords.contains(password)){
            return false;
        } else{
            prevPasswords.add(password);
            password = newPassword;
            return true;
        }
    }

    public String sugu(){

        switch(this.IDcode.substring(0, 1)){
            case "1": return "Naine";
            break;
            case "3": return "Naine";
            break;
            case "5": return "Naine";
            break;
            case "2": return "Mees";
            break;
            case "4": return "Mees";
            break;
            case "6": return "Mees";
            break;
            default: return "Paha!";
        }

    }

    public String sünnikuupäev(){

        String firstPart = IDcode.substring(3,5) + "." + IDcode.substring(5,7) + "."
        switch(this.IDcode.substring(0, 1)){
            case "1":;
            break;
            case "3":;
            break;
            case "5":;
            break;
            case "2":;
            break;
            case "4":;
            break;
            case "6":;
            break;
        }

    }

}

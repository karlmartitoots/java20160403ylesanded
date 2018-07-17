
public class IsikukoodiRegisterTest {

    public static void main(String[] args) {

        IsikukoodiRegister isikukoodiRegister = new IsikukoodiRegister();

        isikukoodiRegister.registreeri("39512064232");
        isikukoodiRegister.registreeri("38532905983");
        isikukoodiRegister.registreeri("40494242489");

        Runnable r = () ->{
            isikukoodiRegister.registreeri("32494023940");
        };

        Runnable r1 = () ->{
            isikukoodiRegister.järjekorranumber("39512064232");
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r1).start();
        new Thread(r1).start();


    }

}

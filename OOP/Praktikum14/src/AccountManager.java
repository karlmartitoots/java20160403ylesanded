import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccountManager {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@dbaprod1:1544:SHR1_PRD", "kmt", "kmt2"
        );

    }

}

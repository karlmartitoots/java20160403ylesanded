package sqlInjection.app;

import org.h2.tools.RunScript;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;

public class Main {

  public static void main(String[] args) throws Exception {
    try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:")) {
      loadInitialData(connection);

      System.out.println("before:");
      dumpTable(connection);

      // account name could come from anywhere:
      // - JavaFX input
      // - command line argument
      // - over the network
      String depositToAccount = "Märt' OR ''=''; " +
              "UPDATE accounts SET balance = balance - 10 WHERE holder_name = 'Märt";
      deposit10(connection, depositToAccount);

      depositToAccount = "Märt'; " +
              "INSERT INTO accounts (holder_name) VALUES ('Karl'); " +
              "UPDATE accounts SET balance = 1000000 WHERE holder_name = 'Karl";
      deposit10(connection, depositToAccount);

      depositToAccount = "Märt'; " +
              "TRUNCATE TABLE accounts ; " +
              "INSERT INTO accounts (holder_name) VALUES ('Karl'); " +
              "UPDATE accounts SET balance = 1337 WHERE holder_name = 'Karl";
      deposit10(connection, depositToAccount);

      System.out.println();
      System.out.println("after:");
      dumpTable(connection);
    }
  }

  private static void deposit10(Connection conn, String holderName) throws SQLException {
    conn.setAutoCommit(false);
    try (PreparedStatement prepareDeposit10 = conn.prepareStatement(
            "UPDATE accounts SET balance = balance + 10 WHERE holder_name = ?")) {
      prepareDeposit10.setString(1, holderName);
      if(prepareDeposit10.executeUpdate() == 1)
        System.out.println("Deposit complete!");
    }catch(SQLException e){
      conn.rollback();
    }finally{
      conn.commit();
      conn.setAutoCommit(true);
    }
  }

  private static void loadInitialData(Connection connection) throws SQLException, IOException {
    try (Reader reader = new InputStreamReader(
        Main.class.getClassLoader().getResourceAsStream("database-setup.sql"), "UTF-8")) {
      RunScript.execute(connection, reader);
    }
  }

  private static void dumpTable(Connection connection) throws SQLException {
    try (PreparedStatement ps = connection.prepareStatement("SELECT * from accounts")) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("holder_name") + "\t" + rs.getBigDecimal("balance"));
      }
    }
  }
}

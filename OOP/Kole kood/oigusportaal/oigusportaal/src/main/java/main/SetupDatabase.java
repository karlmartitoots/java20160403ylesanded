package main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class SetupDatabase {
  public static void main(String[] args) throws Exception {
    try (Connection conn = DriverManager.getConnection("jdbc:h2:~/oigusportaal");
         InputStream is = SetupDatabase.class.getClassLoader().getResourceAsStream("lawportal.sql")) {
      org.h2.tools.RunScript.execute(conn, new InputStreamReader(is, "UTF-8"));
    }
  }
}

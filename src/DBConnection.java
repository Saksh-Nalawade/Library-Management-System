// DBConnection.java
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "Sakshi";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws Exception {
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Get the connection
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // Test the connection
            if (con != null) {
                System.out.println("Successfully connected to the database!");
                return con;
            } else {
                System.out.println("Failed to connect to the database!");
                return null;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            System.err.println("Connection failed! Check output console");
            e.printStackTrace();
            throw e;
        }
    }

    // Test the database connection
    public static boolean testConnection() {
        try {
            Connection con = getConnection();
            if (con != null) {
                con.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

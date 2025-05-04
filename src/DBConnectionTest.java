import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {
    public static void main(String[] args) {
        // Oracle DB configuration
        String url = "jdbc:oracle:thin:@localhost:1521:xe";  // Default Oracle XE
        String username = "Sakshi";
        String password = "123";

        try {
            // Load Oracle JDBC Driver (for ojdbc8 or older)
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // For ojdbc10/11, use: Class.forName("oracle.jdbc.OracleDriver");

            // Establish connection
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("✅ Connection established successfully.");
            }  // Auto-closes the connection
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

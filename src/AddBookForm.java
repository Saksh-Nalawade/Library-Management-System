import javax.swing.*;
import java.sql.*;

public class AddBookForm extends JFrame {
    public AddBookForm() {
        setTitle("Add Book");
        setSize(300, 300);
        setLayout(null);

        JLabel l1 = new JLabel("Title:");
        l1.setBounds(20, 20, 80, 25);
        JTextField t1 = new JTextField();
        t1.setBounds(100, 20, 150, 25);

        JLabel l2 = new JLabel("Author:");
        l2.setBounds(20, 60, 80, 25);
        JTextField t2 = new JTextField();
        t2.setBounds(100, 60, 150, 25);

        JLabel l3 = new JLabel("Category:");
        l3.setBounds(20, 100, 80, 25);
        JTextField t3 = new JTextField();
        t3.setBounds(100, 100, 150, 25);

        JLabel l4 = new JLabel("Quantity:");
        l4.setBounds(20, 140, 80, 25);
        JTextField t4 = new JTextField();
        t4.setBounds(100, 140, 150, 25);

        JButton save = new JButton("Save");
        save.setBounds(100, 190, 100, 30);

        add(l1); add(t1);
        add(l2); add(t2);
        add(l3); add(t3);
        add(l4); add(t4);
        add(save);

        save.addActionListener(e -> {
            try {
                // Validate inputs
                if (t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Title and Author are required!");
                    return;
                }

                int quantity;
                try {
                    quantity = Integer.parseInt(t4.getText());
                    if (quantity < 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be a positive number!");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a number!");
                    return;
                }

                // Get database connection
                Connection con = DBConnection.getConnection();
                
                // Test the connection
                if (con == null) {
                    JOptionPane.showMessageDialog(this, "Could not connect to database!");
                    return;
                }

                // Insert the book using sequence
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Sakshi.Books (id, title, author, category, quantity) " +
                    "VALUES (Sakshi.book_id_seq.NEXTVAL, ?, ?, ?, ?)");
                ps.setString(1, t1.getText().trim());
                ps.setString(2, t2.getText().trim());
                ps.setString(3, t3.getText().trim());
                ps.setInt(4, quantity);
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Book Added Successfully!");
                    // Clear the form
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add book!");
                }
                
                // Close resources
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Database Error: " + ex.getMessage() + 
                    "\nError Code: " + ex.getErrorCode() +
                    "\nSQL State: " + ex.getSQLState());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

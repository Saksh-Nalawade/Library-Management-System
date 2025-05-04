import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ReportForm extends JFrame {
    public ReportForm() {
        setTitle("Library Reports");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton booksBtn = new JButton("Books Report");
        JButton issuedBtn = new JButton("Issued Books");
        JButton overdueBtn = new JButton("Overdue Books");

        buttonPanel.add(booksBtn);
        buttonPanel.add(issuedBtn);
        buttonPanel.add(overdueBtn);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        booksBtn.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Books");
                StringBuilder report = new StringBuilder("Books Report:\n\n");
                while (rs.next()) {
                    report.append("ID: ").append(rs.getInt(1))
                          .append(", Title: ").append(rs.getString(2))
                          .append(", Author: ").append(rs.getString(3))
                          .append(", Category: ").append(rs.getString(4))
                          .append(", Quantity: ").append(rs.getInt(5))
                          .append("\n");
                }
                reportArea.setText(report.toString());
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        issuedBtn.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "SELECT i.issue_id, b.title, i.student_id, i.issue_date, i.due_date, i.return_date, i.fine " +
                    "FROM IssuedBooks i JOIN Books b ON i.book_id = b.id " +
                    "WHERE i.return_date IS NULL");
                StringBuilder report = new StringBuilder("Currently Issued Books:\n\n");
                while (rs.next()) {
                    report.append("Issue ID: ").append(rs.getInt(1))
                          .append(", Book: ").append(rs.getString(2))
                          .append(", Student ID: ").append(rs.getInt(3))
                          .append(", Issue Date: ").append(rs.getDate(4))
                          .append(", Due Date: ").append(rs.getDate(5))
                          .append("\n");
                }
                reportArea.setText(report.toString());
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        overdueBtn.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "SELECT i.issue_id, b.title, i.student_id, i.issue_date, i.due_date, i.fine " +
                    "FROM IssuedBooks i JOIN Books b ON i.book_id = b.id " +
                    "WHERE i.return_date IS NULL AND SYSDATE > i.due_date");
                StringBuilder report = new StringBuilder("Overdue Books:\n\n");
                while (rs.next()) {
                    report.append("Issue ID: ").append(rs.getInt(1))
                          .append(", Book: ").append(rs.getString(2))
                          .append(", Student ID: ").append(rs.getInt(3))
                          .append(", Issue Date: ").append(rs.getDate(4))
                          .append(", Due Date: ").append(rs.getDate(5))
                          .append(", Fine: $").append(rs.getDouble(6))
                          .append("\n");
                }
                reportArea.setText(report.toString());
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
} 
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.Calendar;

public class IssueBookForm extends JFrame {
    public IssueBookForm() {
        setTitle("Issue Book");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel bookIdLabel = new JLabel("Book ID:");
        JTextField bookIdField = new JTextField();
        
        JLabel studentIdLabel = new JLabel("Student ID:");
        JTextField studentIdField = new JTextField();
        
        JLabel issueDateLabel = new JLabel("Issue Date:");
        JTextField issueDateField = new JTextField();
        issueDateField.setText(new Date(System.currentTimeMillis()).toString());
        issueDateField.setEditable(false);
        
        JLabel dueDateLabel = new JLabel("Due Date:");
        JTextField dueDateField = new JTextField();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 14); // 2 weeks from today
        dueDateField.setText(new Date(cal.getTimeInMillis()).toString());
        dueDateField.setEditable(false);

        JButton issueButton = new JButton("Issue Book");
        
        add(bookIdLabel);
        add(bookIdField);
        add(studentIdLabel);
        add(studentIdField);
        add(issueDateLabel);
        add(issueDateField);
        add(dueDateLabel);
        add(dueDateField);
        add(new JLabel());
        add(issueButton);

        issueButton.addActionListener(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText());
                int studentId = Integer.parseInt(studentIdField.getText());
                Date issueDate = Date.valueOf(issueDateField.getText());
                Date dueDate = Date.valueOf(dueDateField.getText());

                IssueDAO dao = new IssueDAO();
                dao.issueBook(bookId, studentId, issueDate, dueDate);
                JOptionPane.showMessageDialog(this, "Book issued successfully!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
} 
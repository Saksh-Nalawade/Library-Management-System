import javax.swing.*;
import java.awt.*;

public class ReturnBookForm extends JFrame {
    public ReturnBookForm() {
        setTitle("Return Book");
        setSize(300, 150);
        setLayout(new GridLayout(2, 2, 10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel issueIdLabel = new JLabel("Issue ID:");
        JTextField issueIdField = new JTextField();
        
        JButton returnButton = new JButton("Return Book");
        
        add(issueIdLabel);
        add(issueIdField);
        add(new JLabel());
        add(returnButton);

        returnButton.addActionListener(e -> {
            try {
                int issueId = Integer.parseInt(issueIdField.getText());
                IssueDAO dao = new IssueDAO();
                dao.returnBook(issueId);
                JOptionPane.showMessageDialog(this, "Book returned successfully!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
} 
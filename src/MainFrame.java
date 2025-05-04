// MainFrame.java
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Library Management System");
        setSize(600, 400);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addBtn = new JButton("Add Book");
        addBtn.addActionListener(e -> new AddBookForm());

        JButton searchBtn = new JButton("Search Books");
        searchBtn.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog(this, "Enter search keyword:");
            if (keyword != null && !keyword.trim().isEmpty()) {
                try {
                    BookDAO dao = new BookDAO();
                    java.util.List<Book> books = dao.searchBooks(keyword);
                    StringBuilder result = new StringBuilder();
                    for (Book book : books) {
                        result.append("ID: ").append(book.getId())
                              .append(", Title: ").append(book.getTitle())
                              .append(", Author: ").append(book.getAuthor())
                              .append(", Category: ").append(book.getCategory())
                              .append(", Quantity: ").append(book.getQuantity())
                              .append("\n");
                    }
                    JOptionPane.showMessageDialog(this, 
                        result.length() > 0 ? result.toString() : "No books found!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        });

        JButton issueBtn = new JButton("Issue Book");
        issueBtn.addActionListener(e -> new IssueBookForm());

        JButton returnBtn = new JButton("Return Book");
        returnBtn.addActionListener(e -> new ReturnBookForm());

        JButton reportBtn = new JButton("Reports");
        reportBtn.addActionListener(e -> new ReportForm());

        add(addBtn);
        add(searchBtn);
        add(issueBtn);
        add(returnBtn);
        add(reportBtn);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

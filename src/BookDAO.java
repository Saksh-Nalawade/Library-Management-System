// BookDAO.java
import java.sql.*;
import java.util.*;

public class BookDAO {
    public void addBook(Book book) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO Sakshi.Books (id, title, author, category, quantity) " +
                    "VALUES (Sakshi.book_id_seq.NEXTVAL, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setString(3, book.getCategory());
        ps.setInt(4, book.getQuantity());
        ps.executeUpdate();
        con.close();
    }

    public List<Book> searchBooks(String keyword) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM Sakshi.Books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR LOWER(category) LIKE ?";
        PreparedStatement ps = con.prepareStatement(sql);
        keyword = "%" + keyword.toLowerCase() + "%";
        ps.setString(1, keyword);
        ps.setString(2, keyword);
        ps.setString(3, keyword);
        ResultSet rs = ps.executeQuery();
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            list.add(b);
        }
        con.close();
        return list;
    }

    // add updateBook(), deleteBook(), getBookById() similarly
}

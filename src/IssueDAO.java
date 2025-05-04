import java.sql.*;

public class IssueDAO {
    // Issue a book
    public void issueBook(int bookId, int studentId, Date issueDate, Date dueDate) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO Sakshi.IssuedBooks (issue_id, book_id, student_id, issue_date, due_date, return_date, fine) " +
                     "VALUES (issue_seq.NEXTVAL, ?, ?, ?, ?, NULL, 0)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bookId);
        ps.setInt(2, studentId);
        ps.setDate(3, issueDate);
        ps.setDate(4, dueDate);
        ps.executeUpdate();
        con.close();
    }

    // Return a book with fine calculation
    public void returnBook(int issueId) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE IssuedBooks SET return_date = SYSDATE, fine = " +
                     "CASE WHEN SYSDATE > due_date THEN (SYSDATE - due_date) * 2 ELSE 0 END " +
                     "WHERE issue_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, issueId);
        ps.executeUpdate();
        con.close();
    }
} 
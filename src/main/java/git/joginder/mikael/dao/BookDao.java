package git.joginder.mikael.dao;

import git.joginder.mikael.model.Book;
import git.joginder.mikael.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//Handles CRUD (Create, Read, Update, Delete) operations with JDBC.
public class BookDao {
    Connection connection;

    public BookDao(){
        connection = DBConnection.getConnection();
    }

    public void addBook(Book book) {
        String sql  = "INSERT INTO books (title, author, year, isbn) VALUES (?, ?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setString(4, book.getIsbn());
            ps.executeUpdate(); //executeUpdate() for INSERT, UPDATE, DELETE
        }catch(SQLException e){
            IO.println("FAILED. " +e.getMessage());
        }
    }

    public Book getBookById(int id){
        String sql = "SELECT * FROM books WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){ //executeQuery() for SELECT
                if(rs.next()){
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setYear(rs.getInt("year"));
                    book.setIsbn(rs.getString("isbn"));
                    return book;
                }
            }
        }catch(SQLException e){
            IO.println("FAILED. " + e.getMessage());
        }
        return null;
    }

    public List<Book> getAllBook(){
        return null;
    }

    public void updateBook(Book book){

    }

    public void deleteBook(int id){

    }

    public void batchInsertBooks(List<Book> books){

    }
}

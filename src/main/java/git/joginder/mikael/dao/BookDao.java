package git.joginder.mikael.dao;

import git.joginder.mikael.model.Book;
import git.joginder.mikael.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            ps.executeUpdate();
        }catch(SQLException e){
            IO.println("FAILED. " +e.getMessage());
        }
    }

    public Book getBookById(int id){

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

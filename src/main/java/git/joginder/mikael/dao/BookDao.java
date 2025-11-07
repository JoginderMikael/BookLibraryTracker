package git.joginder.mikael.dao;

import git.joginder.mikael.model.Book;
import git.joginder.mikael.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Handles CRUD (Create, Read, Update, Delete) operations with JDBC.
public class BookDao {
    Connection connection;
    public BookDao(Connection connection){
        this.connection = connection;
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
        String sql = "SELECT * FROM books";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                List<Book> books = new ArrayList<>();
                while(rs.next()){
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setYear(rs.getInt("year"));
                    book.setIsbn(rs.getString("isbn"));
                    books.add(book);
                }
                return books;
            }
        }catch(SQLException e){
            IO.println("Failed to Fetch Books" + e.getMessage()) ;
        }
        return null;
    }

    public boolean updateBook(Book book){
        StringBuilder sql = new StringBuilder("UPDATE books SET ");
        List<Object> params = new ArrayList<>();

        //title, author, year, isbn
        if(book.getTitle() != null && !book.getTitle().isEmpty()){
            sql.append("title = ?, ");
            params.add(book.getTitle());
        }

        if(book.getAuthor() != null && !book.getAuthor().isEmpty()){
            sql.append("author = ?, ");
            params.add(book.getAuthor());
        }

        if(book.getYear() != 0){
            sql.append("year = ?, ");
            params.add(book.getYear());
        }

        if(book.getIsbn() != null && !book.getIsbn().isEmpty()){
            sql.append("isbn = ?, ");
            params.add(book.getIsbn());
        }

    if(params.isEmpty()){
        IO.println("NOTHING TO UPDATE");
        return false;
    }

    if(book.getId() == 0){
        IO.println("CAN'T UPDATE BOOK WITH NO ID");
        return false;
    }

        sql.setLength(sql.length() - 2); //strimming the last , and space
        sql.append(" WHERE id = ?");
        params.add(book.getId());

    try(PreparedStatement ps = connection.prepareStatement(sql.toString())){
        for(int i = 0; i < params.size(); i++){
            ps.setObject(i + 1, params.get(i));
        }
        ps.executeUpdate();
        return true;
    }catch(SQLException e){
        IO.println("Book Update Failed. " + e.getMessage());
        return false;
    }

    }

    public boolean deleteBook(int id){
        String sql = "DELETE FROM books WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            IO.println("Deletion Failed. " + e.getMessage());
            return false;
        }

    }

    public void batchInsertBooks(List<Book> books){
        String sql  = "INSERT INTO books (title, author, year, isbn) VALUES (?, ?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            connection.setAutoCommit(false); //disable auto-commit for batch efficiency
            for(Book book : books){
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setInt(3, book.getYear());
                ps.setString(4, book.getIsbn());
                ps.addBatch();
            }
            ps.executeBatch(); //execute all inserts at once
            connection.commit(); //commit the transaction.
        }catch (SQLException e){
            IO.println("BATCH INSERT FAILED. " + e.getMessage());
            try{
                if (connection != null){
                    connection.rollback(); //roll back because auto commit had been disabled
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try{
                if(connection != null){
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                IO.println("Error resetting Autocommit: " + e.getMessage());
            }
        }
    }
}

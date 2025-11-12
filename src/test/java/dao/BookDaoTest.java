package dao;

import git.joginder.mikael.dao.BookDao;
import git.joginder.mikael.model.Book;
import git.joginder.mikael.util.DBConnection;
import git.joginder.mikael.util.JsonUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class BookDaoTest {

    Connection dbConnection;
    BookDao bookDao;
    @BeforeEach
    void setup(){
        dbConnection = DBConnection.getConnection();
        bookDao = new BookDao(dbConnection);
    }

    @Test
    void testAddBook(){

        Book book = new Book();
        book.setTitle("The Year of Learning");
        book.setYear(2025);
        book.setIsbn("985993847465");
        assertDoesNotThrow(() -> {
            bookDao.addBook(book);
        });
    }
    @Test
    void testGetBookById(){
        assertNotNull(bookDao.getBookById(21));
    }
    @Test
    void testUpdateBook(){
        Book book = new Book();
        book.setId(21);
        book.setAuthor("Joginder Mk1");
        boolean b = bookDao.updateBook(book);
        assertTrue(b);
    }
    @Test
    void testDeleteBook(){
        assertTrue(bookDao.deleteBook(21));
    }
    @AfterEach
    void tearDown() throws SQLException {
        dbConnection.close();
    }

}

package dao;

import git.joginder.mikael.dao.BookDao;
import git.joginder.mikael.util.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

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

    }
    @Test
    void testGetBookById(){

    }
    @Test
    void testUpdateBook(){

    }
    @Test
    void testDeleteBook(){

    }
    @AfterEach
    void tearDown(){

    }

}

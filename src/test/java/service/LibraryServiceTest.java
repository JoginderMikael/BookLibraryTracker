package service;

import git.joginder.mikael.dao.BookDao;
import git.joginder.mikael.service.LibraryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryServiceTest {

    BookDao mockBookDao;
    LibraryService libraryService;

    @BeforeEach
    void setUp(){
        libraryService = new LibraryService(mockBookDao);
    }

    @Test
    void testValidationLogic(){

    }

    @Test
    void testExportBookToJson(){

    }

    @Test
    void testImportBooksFromJson(){

    }

    @AfterEach
    void tearDown(){

    }
}

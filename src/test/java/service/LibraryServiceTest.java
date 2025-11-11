package service;

import git.joginder.mikael.dao.BookDao;
import git.joginder.mikael.model.Book;
import git.joginder.mikael.service.LibraryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraryServiceTest {

    BookDao mockBookDao;
    LibraryService libraryService;

    @BeforeEach
    void setUp(){
        libraryService = new LibraryService(mockBookDao);
    }

    @Test
    void testValidationLogic(){
        Book invalidBook = new Book(0, null, "Mike", 2024, "273838");
        assertEquals(false, libraryService.addBook(invalidBook));
//        assertThrows(IllegalArgumentException.class, () -> {
//            libraryService.addBook(invalidBook);
//        });
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

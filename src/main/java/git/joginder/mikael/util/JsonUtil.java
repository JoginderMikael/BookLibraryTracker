package git.joginder.mikael.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import git.joginder.mikael.dao.BookDao;
import git.joginder.mikael.model.Book;
import git.joginder.mikael.service.LibraryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    BookDao bookDao;
    public  JsonUtil(){

    }
    public JsonUtil(BookDao bookDao){
        this.bookDao = bookDao;
    }
    public void writeBooksToJson(List<Book> books, Path filePath){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(filePath.toFile(), books);
            IO.println("SUCCESSFULLY WRITTEN TO FILE.");
        } catch (IOException e) {
            IO.println("FAILED TO WRITE. " + e.getMessage());
        }
    }

    public void readBooksFromJson(Path filePath){

        List<Book> books = new ArrayList<>();
        //BookDao bookDao = new BookDao();
        try{
            String content = Files.readString(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            JavaType listOfBooks = typeFactory.constructCollectionType(List.class, Book.class);
            List<Book> bookList =  objectMapper.readValue(content, listOfBooks);
            bookDao.batchInsertBooks(bookList);
            IO.println("BOOKS SUCCESSFULLY IMPORTED");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

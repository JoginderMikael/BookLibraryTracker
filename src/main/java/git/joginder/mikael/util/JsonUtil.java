package git.joginder.mikael.util;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import git.joginder.mikael.model.Book;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JsonUtil {
    public void writeBooksToJson(List<Book> books, Path filePath){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(filePath.toFile(), books);
        } catch (IOException e) {
            IO.println("FAILED TO WRITE. " + e.getMessage());
        }
    }

    public void readBooksFromJson(Path filePath){

    }
}

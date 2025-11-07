package git.joginder.mikael.service;

import git.joginder.mikael.dao.BookDao;
import git.joginder.mikael.model.Book;

import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

//This class handles the business logical. It acts as the middle layer between the DAO and the UI
public class LibraryService {

        BookDao bookDao;
        public LibraryService(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public boolean addBook(Book book){
        if(book.getTitle() == null || book.getTitle().isEmpty()){
            IO.println("CAN'T ADD A BOOK WITH NO TITLE!");
            return false;
        }
        if(book.getAuthor() == null || book.getAuthor().isEmpty()){
            IO.println("CAN'T ADD A BOOK WITHOUT AUTHOR");
            return false;
        }
        if(book.getYear() == 0){
            IO.println("CAN'T ADD A BOOK WITH NO YEAR OF PUBLICATION.");
            return false;
        }
        if(book.getIsbn() == null || book.getIsbn().isEmpty()){
            IO.println("CAN'T ADD A BOOK WITHOUT ISBN");
            return false;
        }

        bookDao.addBook(book);
        return true;
    }

    public Book findBookById(int id){
        return bookDao.getBookById(id);
    }
    public List<Book> listAllBooks(){
        return bookDao.getAllBook();
    }
    public void removeBook(int id){
        if(bookDao.deleteBook(id)){
            IO.println("BOOK RECORD DELETION SUCCESSFUL.");
        }else{
            IO.println("BOOK RECORD DELETION FAILED.");
        }
    }
    public void updateBook(Book book){
        if(bookDao.updateBook(book)){
            IO.println("BOOK RECORDS UPDATE SUCCESSFUL");
        }else {
            IO.println("BOOK RECORDS UPDATE FAILED");
        }

    }

    public void exportBooksToJson(Path filepath){

    }

    public void importFromJson(Path filepath){

    }
    public Map<String, Integer> getBookStatistics(){
        return null;
    }

}

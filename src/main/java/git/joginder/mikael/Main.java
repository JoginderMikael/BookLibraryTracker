package git.joginder.mikael;

import git.joginder.mikael.dao.BookDao;
import git.joginder.mikael.model.Book;
import git.joginder.mikael.service.LibraryService;
import git.joginder.mikael.ui.MainMenu;
import git.joginder.mikael.util.DBConnection;
import git.joginder.mikael.util.JsonUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    static void main() {

        IO.println("********************************");
        IO.println("******BOOK LIBRARY TRACKER******");
        IO.println("********************************");

        Connection connection = null;
        try{
            connection = DBConnection.getConnection();
            BookDao bookDao = new BookDao(connection);
            LibraryService libraryService = new LibraryService(bookDao);
            MainMenu mainMenu = new MainMenu(libraryService);
            mainMenu.displayMenu();
        } catch (Exception e){
            System.err.println("APPLICATION STARTUP ERROR: " + e.getMessage());
           // e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }catch(Exception ex){
                    IO.println("ERROR CLOSING CONNECTION: " + ex.getMessage());

            }
        }
    }
}

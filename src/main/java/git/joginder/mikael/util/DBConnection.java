package git.joginder.mikael.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //ALLOW IMPORTATION OF ENV VARIABLES
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASS");

    public static Connection getConnection(){

        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(SQLException e){
            IO.println("Database connection could not be established. " + e.getMessage());
            return null;
        }
    }
}

package git.joginder.mikael.ui;

import git.joginder.mikael.model.Book;
import git.joginder.mikael.service.LibraryService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {

LibraryService libraryService;
Scanner scanner = new Scanner(System.in);

    public MainMenu(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void displayMenu(){
    boolean exit = false;
    while(!exit){
        IO.println("""
                LIBRARY OPTIONS
                1. ADD BOOK
                2. VIEW ALL BOOKS
                3. FIND BOOK BY ID
                4. UPDATE A BOOK
                5. DELETE A BOOK
                6. EXPORT TO JSON
                7. IMPORT FROM JSON
                8. PRINT STATISTICS
                9. EXIT
                """
        );

        int choice = 0;
        IO.print("CHOICE: ");
        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            IO.println("WRONG ENTRY, PLEASE CHOOSE(1-9)");
            scanner.nextLine();
        }

        final Path backups = Paths.get("backups");
        switch (choice){
                case 1 -> {
                    try {
                        Book book = new Book();
                        String bookTitle;
                        String bookAuthor;
                        int bookYear;
                        String bookIsbn;


                        IO.print("ENTER THE TITLE: ");
                        bookTitle = scanner.nextLine();
                        IO.print("ENTER THE AUTHOR: ");
                        bookAuthor = scanner.nextLine();
                        IO.print("ENTER THE YEAR OF PUBLICATION: ");
                        bookYear = scanner.nextInt();
                        scanner.nextLine();
                        IO.print("ENTER THE ISBN OF THE BOOK: ");
                        bookIsbn = scanner.nextLine();

                        book.setTitle(bookTitle);
                        book.setAuthor(bookAuthor);
                        book.setYear(bookYear);
                        book.setIsbn(bookIsbn);

                        IO.println("------------------------------");
                        if(libraryService.addBook(book)){
                            IO.println("BOOK RECORD ADDED SUCCESSFULLY.");
                        } else{
                            IO.println("FAILED TO ADD BOOK RECORD.");
                        }
                    } catch (Exception e){
                        IO.println(e.getMessage());
                    }
                    IO.println("------------------------------");
                }
                case 2 -> {
                    for (Book book : libraryService.listAllBooks()){
                         IO.println(book.toString());
                        IO.println("------------------------------");
                        }
                }
                case 3 -> {
                    try {
                        IO.print("ENTER THE ID: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();
                        Book book = libraryService.findBookById(bookId);
                        IO.println("------------------------------");
                        IO.println(book.toString());
                    } catch (InputMismatchException ex){
                        IO.println("WRONG INPUT" + ex.getMessage());
                        scanner.nextLine();
                    } catch (Exception e) {
                        IO.println("ERROR " + e.getMessage());
                        scanner.nextLine();
                    }
                    IO.println("------------------------------");
                }
                case 4 ->{
                    try{
                        Book book = new Book();


                        IO.print("ENTER THE BOOK ID: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();
                        book.setId(bookId);


                        IO.print("ENTER THE BOOK TITLE (PRESS ENTER TO SKIP): ");
                        String bookTitle = scanner.nextLine();
                        book.setTitle(bookTitle);


                        IO.print("ENTER THE BOOK AUTHOR (PRESS ENTER TO SKIP): ");
                        String bookAuthor = scanner.nextLine();
                        book.setAuthor(bookAuthor);


                        IO.print("ENTER THE BOOK YEAR (PRESS ENTER TO SKIP): ");
                        String bookYearInput = scanner.nextLine();

                        int bookYear;
                        if (!bookYearInput.isEmpty()) {
                            try {
                                bookYear = Integer.parseInt(bookYearInput);
                                book.setYear(bookYear);
                            } catch (NumberFormatException e) {
                                IO.println("Invalid year format. Skipping value.");
                            }
                        }

                        IO.print("ENTER THE BOOK ISBN (PRESS ENTER TO SKIP): ");
                        String bookIsbn = scanner.nextLine();
                        book.setIsbn(bookIsbn);

                        IO.println("------------------------------");
                        libraryService.updateBook(book);

                    } catch (Exception e){
                        IO.println(e.getMessage());
                        scanner.nextLine();
                    }
                    IO.println("------------------------------");
                }
                case 5 ->{
                    try{
                        IO.print("ENTER THE BOOK ID: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();
                        IO.println("------------------------------");
                        libraryService.removeBook(bookId);
                    } catch (InputMismatchException e){
                        IO.println("INVALID INPUT." + e.getMessage());
                        scanner.nextLine();
                    } catch (Exception ex){
                        IO.println("AN ERROR OCCURRED. " + ex.getMessage());
                    }
                    IO.println("------------------------------");
                }
                case 6 ->{
                    IO.println("EXPORT THE BOOKS TO JSON FILE.");
                    Path filePath = backups.resolve("books.json");
                    IO.println("------------------------------");
                    if(Files.exists(filePath)){
                        libraryService.exportBooksToJson(filePath);
                    }else{
                        try {
                            Files.createDirectories(backups);
                            Files.createFile(filePath);
                            libraryService.exportBooksToJson(filePath);
                        } catch (Exception e) {
                            IO.println("ERROR WHILE WRITING TO FILE. " + e.getMessage());
                        }

                    }
                    IO.println("------------------------------");
                }
                case 7 -> {
                    IO.println("IMPORT THE BOOKS FROM JSON FILE.");
                    Path filePath = backups.resolve("books.json");

                    IO.println("------------------------------");
                    if(Files.exists(filePath)){
                        libraryService.importFromJson(filePath);
                    }else{
                        try {
                            Files.createDirectories(backups);
                            Files.createFile(filePath);
                            libraryService.importFromJson(filePath);
                        } catch (Exception e) {
                            IO.println("ERROR WHILE READING FROM FILE. " + e.getMessage());
                        }

                    }
                    IO.println("------------------------------");
                }
                case 8 ->{
                    IO.println("\n--- Book Statistics by Year ---");

                    Map<Integer, Integer> stats = libraryService.getBookStatistics();

                    if (stats.isEmpty()) {
                        System.out.println("No books with year information found.");
                        return;
                    }

                    stats.forEach((year, count) ->
                            IO.println("Year: " + year + " → " + count + " book(s)")
                    );
                    IO.println("---------------------------------");
                }
                case 9 -> {
                    IO.println("------------------------------");
                    IO.println("THANK YOU FOR USING BOOK TRACKER");
                    IO.println("------------------------------");
                    exit = true;
                }
                default -> IO.println("WRONG CHOICE. PLEASE CHOOSE(1 - 9)");
            }
    }

}

}

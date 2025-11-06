package git.joginder.mikael.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String isbn;

    //BOOK ATTRIBUTES
    // Default constructor
    public Book() {
        this.id = 0;
        this.title = "Unknown";
        this.author = "Unknown";
        this.year = 0;
        this.isbn = "N/A";
    }

    // Parameterized constructor
    public Book(int id, String title, String author, int year, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    //getters
    public String getAuthor(){
        return this.author;
    }
    public String getTitle(){
        return this.title;
    }
    public String getIsbn(){
        return this.isbn;
    }
    public int getId(){
        return this.id;
    }
    public int getYear(){
        return this.year;
    }

    @Override
    public String toString(){
      return "BOOK\n" +
              "ID: " + getId() +
              "\nTitle: " + getTitle() +
              "\nAuthor: " + getAuthor() +
              "\nYear: " + getYear() +
              "\nISBN: " + getIsbn();
    }
}

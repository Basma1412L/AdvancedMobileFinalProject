package com.example.basma.advancedmobilefinalproject;

public class Book {

    String book_Name;
    String book_Status;

    public Book()
    {}

    public Book(String book_Name, String book_Status) {
        this.book_Name = book_Name;
        this.book_Status = book_Status;
    }

    public String getBook_Name() {
        return book_Name;
    }

    public String getBook_Status() {
        return book_Status;
    }

    public void setBook_Status(String book_Status) {
        this.book_Status = book_Status;
    }

    public void setBook_Name(String book_Name) {
        this.book_Name = book_Name;
    }
}

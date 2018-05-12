package com.example.basma.advancedmobilefinalproject;

public class Book {

    String book_Name;
    String book_Status;
    String exchanger_email;
    ExchangeRequest exchange_request;

    public Book(String book_Name, String book_Status, ExchangeRequest exchange_request) {
        this.book_Name = book_Name;
        this.book_Status = book_Status;
        this.exchange_request = exchange_request;
    }

    public void setExchanger_email(String exchanger_email) {
        this.exchanger_email = exchanger_email;
    }

    public ExchangeRequest getExchange_request() {
        return exchange_request;
    }

    public void setExchange_request(ExchangeRequest exchange_request) {
        this.exchange_request = exchange_request;
    }

    public Book()
    {}

    public Book(String book_Name, String book_Status) {
        this.book_Name = book_Name;
        this.book_Status = book_Status;
    }


    public Book(String book_Name, String book_Status,String exchanger_email) {
        this.book_Name = book_Name;
        this.book_Status = book_Status;
        this.exchanger_email=exchanger_email;
    }


    public String getExchanger_email() {
        return exchanger_email;
    }

    public void set_exchanger_email(String exchanger_name) {
        this.exchanger_email = exchanger_name;
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

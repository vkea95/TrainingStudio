package ctc.com.viii.ch07.p05;

import java.util.*;

public class Book {
    private int bookId;
    private String details;

    public Book(int id, String det) {
        bookId = id;
        details = det;
    }

    public void update() {
    }

    public int getID() {
        return bookId;
    }

    public void setID(int id) {
        bookId = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static void print() {
        System.out.println("book print");
    }

    public static void main(String[] args) {
        Book.print();
        SubBook.print();
        Book book = new SubBook(1,"");
        book.print();
        SubBook.print();

    }
}

class SubBook extends Book {

    public SubBook(int id, String det) {
        super(id, det);
    }

//    public static void print() {
//        System.out.println("sub sbook print");
//    }
}
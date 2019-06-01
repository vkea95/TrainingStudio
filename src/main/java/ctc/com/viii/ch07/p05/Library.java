package ctc.com.viii.ch07.p05;

import java.util.HashMap;

public class Library {

    private HashMap<Integer, Book> books;

    public Book addBook(int id, String details) {
        books.putIfAbsent(id, new Book(id, details));
        return books.get(id);
    }

    public boolean removeBook(int id) {
        return books.remove(id) != null;
    }

    public Book findBook(int id) {
        return books.get(id);
    }
}

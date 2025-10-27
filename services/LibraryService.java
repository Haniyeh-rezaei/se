package services;

import models.*;

import java.util.*;

public class LibraryService {

    private Map<String, User> users = new HashMap<>();
    private Map<String, Book> books = new HashMap<>();
    private List<LoanRequest> requests = new ArrayList<>();

    public LibraryService() {
        addDemoBooks();
        users.put("emp", new Employee("emp", "123"));
    }

    private void addDemoBooks() {
        Book b1 = new Book("مهندسی نرم‌افزار", "سامرویل", 2020);
        Book b2 = new Book("برنامه‌نویسی جاوا", "هشلدت", 2019);
        books.put(b1.getId(), b1);
        books.put(b2.getId(), b2);
    }

    public boolean registerStudent(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new Student(username, password));
        return true;
    }

    public User login(String username, String password) {
        if (!users.containsKey(username)) return null;
        User u = users.get(username);
        if (!u.getPassword().equals(password)) return null;
        return u;
    }

    public List<Book> searchBooks(String title, String author, int year) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            boolean ok = true;

            if (!title.isEmpty() && !b.getTitle().contains(title)) ok = false;
            if (!author.isEmpty() && !b.getAuthor().contains(author)) ok = false;
            if (year != 0 && b.getYear() != year) ok = false;

            if (ok) result.add(b);
        }
        return result;
    }

    public boolean requestLoan(String username, String bookId, String start, String end) {
        if (!books.containsKey(bookId)) return false;
        Book b = books.get(bookId);
        if (!b.isAvailable()) return false;
        requests.add(new LoanRequest(username, bookId, start, end));
        b.setAvailable(false);
        return true;
    }
}

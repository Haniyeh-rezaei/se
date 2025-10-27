package models;

import java.util.UUID;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book(String title, String author, int year) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}

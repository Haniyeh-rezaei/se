package models;

public class Loan {
    private String username;
    private String bookId;
    private String startDate;
    private String endDate;
    private boolean returned;

    public Loan(String username, String bookId, String startDate, String endDate) {
        this.username = username;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returned = false;
    }

    public String getUsername() { return username; }
    public String getBookId() { return bookId; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
}


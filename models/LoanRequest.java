package models;

public class LoanRequest {
    private String username;
    private String bookId;
    private String startDate;
    private String endDate;

    public LoanRequest(String username, String bookId, String startDate, String endDate) {
        this.username = username;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUsername() { return username; }
    public String getBookId() { return bookId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
}

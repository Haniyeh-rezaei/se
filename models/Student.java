package models;

public class Student extends User {

    private boolean active;

    public Student(String username, String password) {
        super(username, password, UserRole.STUDENT);
        this.active = true;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

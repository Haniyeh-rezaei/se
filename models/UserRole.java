package models;
/*
        * مدیر سیستم
 */
public class Admin extends User {
    private String name;

    public Admin(String username, String password, String name) {
        super(username, password);
        this.name = name;
    }

    public String getName() { return name; }
}

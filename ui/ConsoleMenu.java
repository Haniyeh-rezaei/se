package ui;

import models.*;
import services.LibraryService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final LibraryService libraryService;

    public ConsoleMenu(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== سیستم مدیریت کتابخانه دانشگاه =====");
            System.out.println("1) ورود");
            System.out.println("2) ثبت نام دانشجو");
            System.out.println("3) خروج");

            System.out.print("انتخاب کنید: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: loginMenu(); break;
                case 2: registerStudent(); break;
                case 3: return;
                default: System.out.println("گزینه نامعتبر");
            }
        }
    }

    private void registerStudent() {
        System.out.print("نام کاربری: ");
        String username = scanner.nextLine();
        System.out.print("رمز عبور: ");
        String password = scanner.nextLine();

        boolean ok = libraryService.registerStudent(username, password);
        System.out.println(ok ? "ثبت نام با موفقیت انجام شد." : "نام کاربری تکراری است.");
    }

    private void loginMenu() {
        System.out.print("نام کاربری: ");
        String username = scanner.nextLine();
        System.out.print("رمز عبور: ");
        String password = scanner.nextLine();

        User user = libraryService.login(username, password);

        if (user == null) {
            System.out.println("نام کاربری یا رمز عبور اشتباه است.");
            return;
        }

        if (user.getRole() == UserRole.STUDENT)
            studentMenu((Student) user);
        else
            System.out.println("این نقش هنوز پیاده‌سازی نشده است.");
    }

    private void studentMenu(Student s) {
        while (true) {
            System.out.println("\n*** منوی دانشجو ***");
            System.out.println("1) جستجوی کتاب");
            System.out.println("2) درخواست امانت کتاب");
            System.out.println("3) خروج");

            System.out.print("انتخاب کنید: ");
            int ch = scanner.nextInt();
            scanner.nextLine();

            switch (ch) {
                case 1: searchBooks(); break;
                case 2: requestLoan(s); break;
                case 3: return;
                default: System.out.println("گزینه نامعتبر");
            }
        }
    }

    private void searchBooks() {
        System.out.print("عنوان (اختیاری): ");
        String title = scanner.nextLine();
        System.out.print("نویسنده (اختیاری): ");
        String author = scanner.nextLine();
        System.out.print("سال (اگر مهم نیست 0 بزنید): ");
        int year = scanner.nextInt();
        scanner.nextLine();

        List<Book> list = libraryService.searchBooks(title, author, year);

        if (list.isEmpty()) {
            System.out.println("کتابی یافت نشد.");
            return;
        }

        System.out.println("\nنتایج:");
        for (Book b : list) {
            System.out.println("کد: " + b.getId() + " | " + b.getTitle() + " | " + b.getAuthor()
                    + " | وضعیت: " + (b.isAvailable() ? "موجود" : "ناموجود"));
        }
    }

    private void requestLoan(Student s) {
        System.out.print("کد کتاب: ");
        String id = scanner.nextLine();
        System.out.print("تاریخ شروع: ");
        String start = scanner.nextLine();
        System.out.print("تاریخ پایان: ");
        String end = scanner.nextLine();

        boolean ok = libraryService.requestLoan(s.getUsername(), id, start, end);

        System.out.println(ok ? "درخواست امانت ثبت شد." : "کتاب موجود نیست یا کد اشتباه است.");
    }
}


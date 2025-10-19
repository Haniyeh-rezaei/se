import models.*;
import services.LibraryService;
import services.ReportService;
import storage.DataStore;

import java.time.LocalDate;
import java.util.*;

/**
 * مثال اجرایی (Demo) — این main نمایی از کارکردهای اصلی سیستم را نشان می‌دهد.
 * کافیست در IntelliJ این فایل را اجرا کنی.
 */
public class Main {
    public static void main(String[] args) {
        LibraryService lib = new LibraryService();
        ReportService rep = new ReportService();

        // -------- راه‌اندازی اولیه: ادمین و سه کارمند ----------
        Admin admin = lib.createAdmin("admin", "adminpass", "مدیر");
        Employee emp1 = lib.addEmployee(admin.getId(), "emp1", "p1", "کارمند 1");
        Employee emp2 = lib.addEmployee(admin.getId(), "emp2", "p2", "کارمند 2");
        Employee emp3 = lib.addEmployee(admin.getId(), "emp3", "p3", "کارمند 3");

        System.out.println("✅ ادمین و 3 کارمند ساخته شدند.");

        // -------- ثبت چند دانشجو ----------
        Student s1 = lib.registerStudent("ali", "1111", "علی رضایی");
        Student s2 = lib.registerStudent("sara", "2222", "سارا حسینی");
        Student s3 = lib.registerStudent("mohammad", "3333", "محمد موسوی");
        System.out.println("✅ دانشجویان ثبت شدند: " + lib.getRegisteredStudentsCount());

        // -------- کارمندان چند کتاب ثبت می‌کنند ----------
        Book b1 = lib.addBook("Clean Code", "Robert C. Martin", 2008, emp1.getId()); // ثبت توسط emp1
        Book b2 = lib.addBook("Design Patterns", "Gamma et al.", 1994, emp2.getId()); // ثبت توسط emp2
        Book b3 = lib.addBook("Introduction to Algorithms", "Cormen", 2009, emp1.getId()); // emp1
        System.out.println("📚 کتاب‌ها ثبت شدند: " + DataStore.books.size());

        // -------- جستجوی ترکیبی (مثال) ----------
        List<Book> found = lib.searchBooks(Optional.of("Clean"), Optional.empty(), Optional.empty());
        System.out.println("📚 نتیجه جستجو: " + found.size() + " کتاب");

        // -------- دانشجو درخواست امانت ثبت می‌کند ----------
        LoanRequest r1 = lib.requestLoan(s1.getId(), b1.getId(), LocalDate.now(), LocalDate.now().plusDays(7));
        LoanRequest r2 = lib.requestLoan(s2.getId(), b2.getId(), LocalDate.now().minusDays(1), LocalDate.now().plusDays(5));
        System.out.println("درخواست امانت ثبت شد؟ " + (r1 != null));

        // -------- کارمند درخواست را تایید می‌کند ----------
        boolean ok1 = lib.approveLoan(emp1.getId(), r1.getId()); // باید true (start = today)
        boolean ok2 = lib.approveLoan(emp2.getId(), r2.getId()); // باید true (start = yesterday)
        System.out.println("درخواست اول تایید شد؟ " + ok1);
        System.out.println("درخواست دوم تایید شد؟ " + ok2);

        // -------- ثبت بازگشت کتاب با تاخیر برای یکی از دانشجویان ----------
        // فرض: r1 دانشجو به موقع برگردانده (همان روز)
        lib.receiveBook(emp1.getId(), r1.getId(), LocalDate.now().plusDays(7));
        // r2 با تاخیر برگردانده شده
        lib.receiveBook(emp2.getId(), r2.getId(), LocalDate.now().plusDays(10)); // تاخیر نسبت به endDate

        // -------- گزارش‌ها ----------
        System.out.println("📊 گزارش کل سیستم: " + rep.getLoanStats());
        System.out.println("📈 گزارش دانشجویان و تاخیرها: " + rep.getStudentStatsAndTopDelay());

        // گزارش عملکرد کارمند emp1
        System.out.println("👷 عملکرد کارمند emp1: " + rep.getEmployeePerformance(emp1.getId()));

        System.out.println("\n✅ سیستم با موفقیت اجرا شد!");
    }
}
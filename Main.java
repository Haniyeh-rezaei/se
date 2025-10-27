import services.LibraryService;
import ui.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        ConsoleMenu menu = new ConsoleMenu(libraryService);
        menu.start();
    }
}

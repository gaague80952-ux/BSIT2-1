import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem library = new LibrarySystem();

        library.addBookSilently(new Book("Clean Code", "Robert C. Martin"));

        System.out.println("===== LIBRARY INFORMATION SYSTEM =====");
        System.out.println("1. Add a book");
        System.out.println("2. List all books");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Search a book");
        System.out.println("0. Exit");

        int choice;

        do {
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title : ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;

                case 2:
                    library.listBooks();
                    break;

                case 3:
                    System.out.print("Enter title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowTitle);
                    break;

                case 4:
                    System.out.print("Enter title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;

                case 5:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchBook(searchTitle);
                    break;

                case 0:
                    System.out.println(">> Thank you for using the Library System. Goodbye!");
                    break;

                default:
                    System.out.println(">> Invalid choice. Please try again.");
            }

            System.out.println();

        } while (choice != 0);

        scanner.close();
    }
}

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    public String describe() {
        String status = isBorrowed ? "Borrowed" : "Available";
        return title + " by " + author + " [" + status + "]";
    }
}

class LibrarySystem {
    private ArrayList<Book> books;

    public LibrarySystem() {
        books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(">> Book added successfully.");
    }

    public void addBookSilently(Book book) {
        books.add(book);
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println(">> No books in the library yet.");
            return;
        }

        System.out.println("--- Library Catalog ---");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            System.out.println((i + 1) + ". " + b.describe());
        }
    }

    public void borrowBook(String title) {
        Book book = findBook(title);

        if (book == null) {
            System.out.println(">> Book not found: " + title);
        } else if (book.isBorrowed()) {
            System.out.println(">> Sorry, '" + title + "' is already borrowed.");
        } else {
            book.borrow();
            System.out.println(">> You borrowed '" + title + "'.");
        }
    }

    public void returnBook(String title) {
        Book book = findBook(title);

        if (book == null) {
            System.out.println(">> Book not found: " + title);
        } else if (!book.isBorrowed()) {
            System.out.println(">> '" + title + "' was not borrowed.");
        } else {
            book.returnBook();
            System.out.println(">> You returned '" + title + "'.");
        }
    }

    public void searchBook(String title) {
        Book book = findBook(title);

        if (book != null) {
            System.out.println(">> Found: " + book.describe());
        } else {
            System.out.println(">> No book found with title: " + title);
        }
    }

    private Book findBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }
}

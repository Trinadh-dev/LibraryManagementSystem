package com.smce.LibraryManagementSystem;




import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.smce.LibraryManagement.Model.Book;
import com.smce.LibraryManagement.Model.BorrowedBook;
import com.smce.LibraryManagement.Model.Member;
import com.smce.LibraryManagement.Services.BookService;
import com.smce.LibraryManagement.Services.BorrowedBookService;
import com.smce.LibraryManagement.Services.MemberService;

public class App {
    private static BookService bookService = new BookService();
    private static MemberService memberService = new MemberService();
    private static BorrowedBookService borrowedBookService = new BorrowedBookService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Library Management System");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Manage Borrowed Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageBooks();
                    break;
                case 2:
                    manageMembers();
                    break;
                case 3:
                    manageBorrowedBooks();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private static void manageBooks() {
        int choice;
        do {
            System.out.println("Manage Books");
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void viewAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            books.forEach(System.out::println);
            System.out.println("Succefull");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        System.out.print("Enter total copies: ");
        int totalCopies = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setAvailableCopies(availableCopies);
        book.setTotalCopies(totalCopies);

        try {
            bookService.addBook(book);
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateBook() {
        System.out.print("Enter book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try {
            Book book = bookService.getAllBooks().stream()
                    .filter(b -> b.getId() == bookId)
                    .findFirst()
                    .orElse(null);

            if (book != null) {
                System.out.print("Enter new book title: ");
                book.setTitle(scanner.nextLine());
                System.out.print("Enter new book author: ");
                book.setAuthor(scanner.nextLine());
                System.out.print("Enter new book publisher: ");
                book.setPublisher(scanner.nextLine());
                System.out.print("Enter new available copies: ");
                book.setAvailableCopies(scanner.nextInt());
                System.out.print("Enter new total copies: ");
                book.setTotalCopies(scanner.nextInt());
                scanner.nextLine();

                bookService.updateBook(book);
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try {
            bookService.deleteBook(bookId);
            System.out.println("Book deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void manageMembers() {
        int choice;
        do {
            System.out.println("Manage Members");
            System.out.println("1. View All Members");
            System.out.println("2. Add Member");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllMembers();
                    break;
                case 2:
                    addMember();
                    break;
                case 3:
                    updateMember();
                    break;
                case 4:
                    deleteMember();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void viewAllMembers() {
        try {
            List<Member> members = memberService.getAllMembers();
            members.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member email: ");
        String email = scanner.nextLine();
        System.out.print("Enter member phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter member address: ");
        String address = scanner.nextLine();

        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);
        member.setAddress(address);

        try {
            memberService.addMember(member);
            System.out.println("Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateMember() {
        System.out.print("Enter member ID to update: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        try {
            Member member = memberService.getAllMembers().stream()
                    .filter(m -> m.getId() == memberId)
                    .findFirst()
                    .orElse(null);

            if (member != null) {
                System.out.print("Enter new member name: ");
                member.setName(scanner.nextLine());
                System.out.print("Enter new member email: ");
                member.setEmail(scanner.nextLine());
                System.out.print("Enter new member phone: ");
                member.setPhone(scanner.nextLine());
                System.out.print("Enter new member address: ");
                member.setAddress(scanner.nextLine());

                memberService.updateMember(member);
                System.out.println("Member updated successfully.");
            } else {
                System.out.println("Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteMember() {
        System.out.print("Enter member ID to delete: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        try {
            memberService.deleteMember(memberId);
            System.out.println("Member deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void manageBorrowedBooks() {
        int choice;
        do {
            System.out.println("Manage Borrowed Books");
            System.out.println("1. View All Borrowed Books");
            System.out.println("2. Add Borrowed Book");
            System.out.println("3. Update Borrowed Book");
            System.out.println("4. Delete Borrowed Book");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllBorrowedBooks();
                    break;
                case 2:
                    addBorrowedBook();
                    break;
                case 3:
                    updateBorrowedBook();
                    break;
                case 4:
                    deleteBorrowedBook();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void viewAllBorrowedBooks() {
        try {
            List<BorrowedBook> borrowedBooks = borrowedBookService.getAllBorrowedBooks();
            borrowedBooks.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBorrowedBook() {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter borrow date (yyyy-mm-dd): ");
        Date borrowDate = Date.valueOf(scanner.nextLine());
        System.out.print("Enter due date (yyyy-mm-dd): ");
        Date dueDate = Date.valueOf(scanner.nextLine());

        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setBookId(bookId);
        borrowedBook.setMemberId(memberId);
        borrowedBook.setBorrowDate(borrowDate);
        borrowedBook.setDueDate(dueDate);

        try {
            borrowedBookService.addBorrowedBook(borrowedBook);
            System.out.println("Borrowed book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateBorrowedBook() {
        System.out.print("Enter borrowed book ID to update: ");
        int borrowedBookId = scanner.nextInt();
        scanner.nextLine();

        try {
            BorrowedBook borrowedBook = borrowedBookService.getAllBorrowedBooks().stream()
                    .filter(b -> b.getId() == borrowedBookId)
                    .findFirst()
                    .orElse(null);

            if (borrowedBook != null) {
                System.out.print("Enter new book ID: ");
                borrowedBook.setBookId(scanner.nextInt());
                System.out.print("Enter new member ID: ");
                borrowedBook.setMemberId(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Enter new borrow date (yyyy-mm-dd): ");
                borrowedBook.setBorrowDate(Date.valueOf(scanner.nextLine()));
                System.out.print("Enter new due date (yyyy-mm-dd): ");
                borrowedBook.setDueDate(Date.valueOf(scanner.nextLine()));
                System.out.print("Enter new return date (yyyy-mm-dd, leave blank if not returned): ");
                String returnDate = scanner.nextLine();
                if (!returnDate.isEmpty()) {
                    borrowedBook.setReturnDate(Date.valueOf(returnDate));
                }
                System.out.print("Enter new fine amount: ");
                borrowedBook.setFine(scanner.nextDouble());
                scanner.nextLine();

                borrowedBookService.updateBorrowedBook(borrowedBook);
                System.out.println("Borrowed book updated successfully.");
            } else {
                System.out.println("Borrowed book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBorrowedBook() {
        System.out.print("Enter borrowed book ID to delete: ");
        int borrowedBookId = scanner.nextInt();
        scanner.nextLine();

        try {
            borrowedBookService.deleteBorrowedBook(borrowedBookId);
            System.out.println("Borrowed book deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


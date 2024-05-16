package service;

import entity.Book;
import entity.User;
import enum1.BookStatus;
import enum1.Type;

import java.util.Objects;
import java.util.Scanner;

import static database.db.*;
import static enum1.BookStatus.*;

public class AdminService {
    static Scanner scanner = new Scanner(System.in);
    static Scanner strScanner = new Scanner(System.in);

    public static void adminService() {
        while (true) {
            switch (adminMenu()) {
                case 0 -> {
                    System.out.println("Bye " + currentUser.getUsername());
                    return;
                }
                case 1 -> {
                    showAllBooks();
                }
                case 2 -> {
                    addBook();
                }
                case 3 -> {
                    editBook();
                }
                case 4 -> {
                    setStatus();
                }
                case 5 -> {
                    orders();
                }
            }
        }
    }

    public static void showAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void addBook() {
        System.out.print("Enter book name: ");
        String name = strScanner.nextLine();
        System.out.print("Choose owner: ");
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        String ownerUsername = strScanner.nextLine();
        User owner = getUserByUsername(ownerUsername);
        if (owner == null) {
            System.out.println("Invalid owner username.");
            return;
        }
        System.out.println("Choose book type: ");
        Type[] types = Type.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }
        int typeChoice = scanner.nextInt();
        Type type = types[typeChoice - 1];
        scanner.nextLine();
        System.out.println("Enter date of lease:");
        String dateOfLease = scanner.nextLine();

        Book newBook = new Book(generateId(), name, owner, BookStatus.NEW, NOT_BORROW, type, dateOfLease);
        books.add(newBook);
        System.out.println("Book added successfully.");
    }

    public static void editBook() {
        showAllBooks();
        System.out.println("Enter the ID of the book you want to edit:");
        String id = strScanner.nextLine();
        Book bookToEdit = null;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                bookToEdit = book;
                break;
            }
        }
        if (bookToEdit == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.println("Enter new book name (leave blank to keep unchanged):");
        String newName = strScanner.nextLine();
        if (!newName.isEmpty()) {
            bookToEdit.setBookName(newName);
        }
        System.out.println("Choose new owner (leave blank to keep unchanged):");
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        String newOwnerUsername = strScanner.nextLine();
        if (!newOwnerUsername.isEmpty()) {
            User newOwner = getUserByUsername(newOwnerUsername);
            if (newOwner == null) {
                System.out.println("Invalid owner username.");
                return;
            }
            bookToEdit.setOwner(newOwner);
        }
        System.out.println("Enter new book status (leave blank to keep unchanged):");
        System.out.println("Available options: NEW, OLD ");
        String newStatusStr = strScanner.nextLine().toUpperCase();
        if (!newStatusStr.isEmpty()) {
            BookStatus newStatus = BookStatus.valueOf(newStatusStr);
            bookToEdit.setStatus(newStatus);
        }
        System.out.println("Enter new book borrowing (leave blank to keep unchanged):");
        System.out.println("Available options: BORROW, NOT_BORROW ");
        String newBorrowStr = strScanner.nextLine().toUpperCase();
        if (!newBorrowStr.isEmpty()) {
            BookStatus newBorrow = BookStatus.valueOf(newBorrowStr);
            bookToEdit.setBorrowing(newBorrow);
        }
        System.out.println("Enter new book type (leave blank to keep unchanged):");
        System.out.println("Available options: ACADEMY, COMEDY, ROMANTIC, BUSINESS, PSICHOLOGY");
        String newTypeStr = strScanner.nextLine().toUpperCase();
        if (!newTypeStr.isEmpty()) {
            Type newType = Type.valueOf(newTypeStr);
            bookToEdit.setType(newType);
        }
        System.out.println("Enter new date of lease (leave blank to keep unchanged):");
        String newDateOfLease = strScanner.nextLine();
        if (!newDateOfLease.isEmpty()) {
            bookToEdit.setDateOfLease(newDateOfLease);
        }
        System.out.println("Book edited successfully.");
    }

    public static void setStatus() {
        System.out.println("Enter the ID of the book you want to set status for:");
        String id = strScanner.nextLine();
        Book bookToSetStatus = null;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                bookToSetStatus = book;
                break;
            }
        }
        if (bookToSetStatus == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.println("Enter new status:");
        System.out.println("Available options: NEW, OLD, BORROW");
        BookStatus newStatus = BookStatus.valueOf(strScanner.nextLine().toUpperCase());
        bookToSetStatus.setStatus(newStatus);
        System.out.println("Enter new borrowing:");
        System.out.println("Available options: BORROW, NOT_BORROW");
        BookStatus newBorrow = BookStatus.valueOf(strScanner.nextLine().toUpperCase());
        bookToSetStatus.setBorrowing(newBorrow);
        System.out.println("Status updated successfully.");
    }

    public static void orders() {
        if (userBooks.size() == 0) {
            System.out.println("No orders!");
            return;
        }
        boolean test = true;
        for (User i : userBooks.keySet()) {
            for (Book j : userBooks.get(i)) {
                if (Objects.equals(j.getBorrowing(), NOT_BORROW)) {
                    System.out.println(j);
                    test = false;
                }
            }
        }
        if (test) {
            System.out.println("No orders!");
            return;
        }

        Book book = null;
        label:
        while (true) {
            System.out.println("Enter id:");
            String id = strScanner.nextLine();
            for (User i : userBooks.keySet()) {
                for (Book j : userBooks.get(i)) {
                    if (Objects.equals(j.getId(), id)) {
                        book = j;
                        break label;
                    }
                }
            }
            System.out.println("Id is invalid!");
        }

        System.out.println("""
                0 -> Back
                1 -> Borrow
                2 -> Cancel
                """);
        switch (scanner.nextInt()) {
            case 0 -> {
                return;
            }
            case 1 -> {
                book.setBorrowing(BORROW);
                System.out.println("Book borrowed!");
            }
            case 2 -> {
                book.setBorrowing(NOT_BORROW);
                System.out.println("Book canceled");
            }
        }
    }

    private static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static int adminMenu() {
        System.out.println("""
                0 -> exit
                1 -> Show all books
                2 -> Add book
                3 -> Edit book
                4 -> Set status
                5 -> Orders                                
                """);
        return scanner.nextInt();
    }
}
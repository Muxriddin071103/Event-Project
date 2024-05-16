package service;

import database.db;
import entity.Book;
import entity.User;
import enum1.*;

import java.util.*;

import static database.db.*;
import static enum1.BookStatus.BORROW;
import static enum1.BookStatus.NOT_BORROW;

public class UserService {
    static Scanner scanner = new Scanner(System.in);
    static Scanner strScanner = new Scanner(System.in);

    public static void userService() {
        while (true) {
            switch (userMenu()) {
                case 0 -> {
                    System.out.println("Bye bye " + currentUser.getUsername());
                    return;
                }
                case 1 -> {
                    showBooksByType();
                }
                case 2 -> {
                    showAllBook();
                }
                case 3 -> {
                    bookRental(currentUser);
                }
                case 4 -> {
                    showMyRentedBooks();
                }
                case 5 -> {
                    returnBook(currentUser);
                }
                case 6 -> {
                    fillBalance();
                }
            }
        }
    }

    public static void showBooksByType() {
        System.out.println("Enter the type of book you want to see: ");
        System.out.println("Available types: ACADEMY, COMEDY, ROMANTIC, BUSINESS, PSYCHOLOGY");
        String typeStr = strScanner.next().toUpperCase();

        Type type = null;
        try {
            type = Type.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type. Please enter one of the available types.");
            return;
        }

        boolean found = false;
        for (Book book : books) {
            if (type == book.getType()) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found for the specified type.");
        }
    }

    public static void showAllBook() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void bookRental(User user) {
        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        showAllBook();
        Book book;
        label:
        while (true) {
            System.out.println("Enter id:");
            String id = strScanner.nextLine();
            for (Book i : books) {
                if (Objects.equals(i.getId(), id)) {
                    book = i;
                    break label;
                }
            }
            System.out.println("Id is invalid!");
        }
        book.setBorrowing(BookStatus.NOT_BORROW);
        book.setOwner(user);
        System.out.println("Enter date:");
        book.setDateOfLease(strScanner.nextLine());

        if (!userBooks.containsKey(user)) {
            userBooks.put(user, new ArrayList<>());
            userBooks.get(user).add(book);
        } else {
            userBooks.get(user).add(book);
        }
        System.out.println("request sent!");
    }

    public static void showMyRentedBooks() {
        System.out.println("Books rented by " + currentUser.getUsername() + ":");
        boolean found = false;
        for (Map.Entry<User, List<Book>> entry : userBooks.entrySet()) {
            User user = entry.getKey();
            List<Book> userBookList = entry.getValue();
            if (user.equals(currentUser)) {
                for (Book book : userBookList) {
                    if (book.getBorrowing().equals(BORROW)) {
                        System.out.println(book);
                        found = true;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("You haven't rented any books yet.");
        }
    }

    public static void returnBook(User user) {
        if (userBooks.isEmpty() || !userBooks.containsKey(user) || userBooks.get(user).isEmpty()) {
            System.out.println("You haven't borrowed any books yet.");
            return;
        }

        System.out.println("Books rented by " + user.getUsername() + ":");
        List<Book> userBookList = userBooks.get(user);
        boolean found = false;
        for (Book book : userBookList) {
            if (book.getBorrowing().equals(BORROW)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("You haven't rented any books currently.");
            return;
        }

        System.out.println("Enter the ID of the book you want to return:");
        String id = strScanner.nextLine();
        for (Book book : userBookList) {
            if (Objects.equals(book.getId(), id)) {
                book.setBorrowing(NOT_BORROW);
                System.out.println("Book with ID " + id + " returned successfully.");
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found in your rented books.");
    }

    public static void fillBalance() {
        System.out.println("Current balance of  " + currentUser.getUsername() + " is " + currentUser.getBalance());
        System.out.println("Enter the amount you want to add to your balance: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        currentUser.setBalance(currentUser.getBalance() + amount);

        System.out.println("Amount added to your balance successfully.");
    }

    public static int userMenu() {
        System.out.println("""
                0 -> exit
                1 -> Show books by type
                2 -> Show all book
                3 -> Book rental
                4 -> Show my rented books
                5 -> Return book
                6 -> Fill balance
                """);
        return scanner.nextInt();
    }
}

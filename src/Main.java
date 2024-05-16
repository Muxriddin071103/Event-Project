import entity.User;
import enum1.Role;

import java.util.Objects;

import static Database.Database.*;
import static service.AdminServices.*;
import static service.UserServices.*;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("""
                    0 -> Exit 
                    1 -> Sign Up
                    2 -> Sign In
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    System.out.printf("See you soon %s\n", currentUser.getUsername());
                    return;
                }
                case 1 -> {
                    SignUp();
                }
                case 2 -> {
                    SignIn();
                }
            }
        }
    }

    private static void SignIn() {
        System.out.print("Enter Username: ");
        String username = strScanner.nextLine();
        System.out.print("Enter Password: ");
        String password = strScanner.nextLine();
        if (Objects.equals(username, "admin") && Objects.equals(password, "admin")) {
            // Assuming you have a special admin user object, set currentAdmin accordingly
            currentAdmin = new User("admin", "admin");
            adminMenu();
            return;
        }
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)) {
                currentUser = user;
                userMenu();
                return;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
    }

    private static void SignUp() {
        System.out.print("Enter Username: ");
        String username = strScanner.nextLine();
        System.out.print("Enter Password: ");
        String password = strScanner.nextLine();
//        System.out.println("Enter balance: ");
//        Double balance = scanner.nextDouble();
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(),password)) {
                System.out.println("User already exists. Please choose a different one.");
                return;
            }
        }
        User newUser = new User(username, password, Role.USER,0.0);
        users.add(newUser);
        System.out.println("User created successfully. You can now sign in.");
    }
}

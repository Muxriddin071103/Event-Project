import entity.User;
import enum1.Role;

import java.util.Objects;
import java.util.Scanner;

import static database.db.*;
import static service.AdminService.*;
import static service.UserService.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Scanner strScanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            switch (menu()) {
                case 0 -> {
                    System.out.println("Bye bye!");
                    return;
                }
                case 1 -> {
                    signIn();
                }
                case 2 -> {
                    signUp();
                }
            }
        }
    }

    public static void signUp() {
        User user = new User();
        System.out.print("Enter username: ");
        user.setUsername(strScanner.nextLine());
        System.out.print("Enter password: ");
        user.setPassword(strScanner.nextLine());
        System.out.println("Enter balance: ");
        user.setBalance(scanner.nextDouble());
        user.setRole(Role.USER);
        if (users.add(user)) {
            System.out.println("User successfully added!");
            return;
        }
        System.out.println("User already exists!");
    }

    public static void signIn() {
        System.out.println("Enter username: ");
        String username = strScanner.nextLine();
        System.out.println("Enter password: ");
        String password = strScanner.nextLine();
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username) &&
                    Objects.equals(user.getPassword(),password)) {
                currentUser = user;
                break;
            }
            currentUser = null;
        }
        if (currentUser == null) {
            System.out.println("User not found!");
            return;
        }
        switch (currentUser.getRole().name()) {
            case "USER" -> {
                userService();
            }
            case "ADMIN" -> {
                adminService();
            }
        }
    }

    public static int menu() {
        System.out.println("""
                0 -> EXIT
                1 -> SIGN IN
                2 -> SIGN UP
                """);
        return scanner.nextInt();
    }
}


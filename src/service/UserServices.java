package service;

import entity.Event;
import entity.Ticket;
import enum1.Status;

import java.util.Objects;

import static Database.Database.*;

public class UserServices {
    public static UserServices userService;
    public static UserServices getInstance(){
        if (userService == null)
            userService=new UserServices();
        return userService;
    }

    public static void userMenu() {
        while (true) {
            System.out.println("""
                    0 exit 
                    1 show events
                    2 show my event
                    3 register
                    4 fill balance
                    5 cancel event
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    System.out.printf("see you soon %s\n", currentUser.getUsername());
                    return;
                }
                case 1 -> {
                    showEvents();
                }
                case 2 -> {
                    showMyEvents();
                }
                case 3 -> {
                    showEvents();
                    System.out.println("enter id");
                    String id = strScanner.nextLine();
                    booking(id);
                }
                case 4 -> { fillBalance(currentUser.getBalance());}
                case 5 -> { cancelEvent(currentUser.getId()); }
            }
        }
    }

    public static void booking(String eventId) {
        for (Event event : events) {
            if (Objects.equals(event.getId(), eventId)) {
                if (event.getStatus() == Status.ACTIVE) {
                    Integer capacity = event.getCapacity();
                    if (capacity > getAllUsers(event)) {
                        Ticket ticket = new Ticket();
                        ticket.setUser(currentUser);
                        ticket.setEvent(event);
                        tickets.add(ticket);
                        System.out.println("Booking successful!");
                        return;
                    } else {
                        System.out.println("There are no available seats");
                        return;
                    }
                } else {
                    System.out.println("Event is not active. Cannot book.");
                    return;
                }
            }
        }
        System.out.println("Event not found!");
    }

    private static int getAllUsers(Event event) {
        int cap = 0;
        for (Ticket ticket : tickets) {
            if (Objects.equals(ticket.getEvent(), event)) {
                cap++;
            }
        }
        return cap;
    }

    public static void showEvents() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public static void showEvent(String id) {
        for (Event event : events) {
            if (Objects.equals(event.getId(), id)) {
                System.out.println(event);
                return;
            }
        }
    }

    public static void showMyEvents(){
        for (Ticket ticket : tickets) {
            if (Objects.equals( ticket.getUser(), currentUser )){
                System.out.println(ticket.getEvent());
            }
        }
    }

    public static void fillBalance(Double amount) {
        if (currentUser != null) {
            if (currentUser.getBalance() != null) {
                System.out.println("Enter amount of money: ");
                amount = scanner.nextDouble();
                currentUser.setBalance(currentUser.getBalance() + amount);
                System.out.println("Balance filled successfully. New balance: " + currentUser.getBalance());
            } else {
                System.out.println("Error: User's balance is null.");
            }
        } else {
            System.out.println("No user logged in.");
        }
    }

    public static void cancelEvent(String eventId) {
        showMyEvents();
        if (currentUser != null) {
            System.out.println("Enter the id of event: ");
            eventId = strScanner.nextLine();
            for (Ticket ticket : tickets) {
                if (Objects.equals(ticket.getEvent().getId(), eventId) && Objects.equals(ticket.getUser().getId(), currentUser.getId())) {
                    tickets.remove(ticket);
                    System.out.println("Event canceled successfully.");
                    return;
                }
            }
            System.out.println("Event not found in your bookings.");
        } else {
            System.out.println("No user logged in.");
        }
    }


}

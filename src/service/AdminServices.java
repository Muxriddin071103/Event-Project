package service;

import entity.*;
import enum1.Status;

import static Database.Database.*;
import static service.UserServices.*;

public class AdminServices {
    public static void adminMenu() {
        while (true) {
            System.out.println("""
                    0 exit 
                    1 add event
                    2 show events
                    3 show event
                    4 edit event
                    5 delete event
                    6 show users by event
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    System.out.printf("see you soon %s\n", currentAdmin.getUsername());
                    return;
                }
                case 1 -> {
                    addEvent();
                }
                case 2 -> {
                    showEvents();
                }
                case 3 -> {
                    showEvents();
                    System.out.println("enter id");
                    String id = strScanner.nextLine();
                    showEvent(id);
                }
                case 4 -> { editEvent(); }
                case 5 -> { deleteEvent(); }
                case 6 -> { showUsersByEvent(); }
            }
        }
    }

    private static void showUsersByEvent() {
        System.out.println("Enter event ID:");
        String eventId = strScanner.nextLine();
        for (Ticket ticket : tickets) {
            if (ticket.getEvent().getId().equals(eventId)) {
                System.out.println("User: " + ticket.getUser().getUsername());
            }
        }
    }

    private static void deleteEvent() {
        System.out.println("Enter event ID:");
        String eventId = strScanner.nextLine();
        Event eventToDelete = null;
        for (Event event : events) {
            if (event.getId().equals(eventId)) {
                eventToDelete = event;
                break;
            }
        }
        if (eventToDelete != null) {
            events.remove(eventToDelete);
            System.out.println("Event deleted successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    private static void editEvent() {
        System.out.println("Enter event ID:");
        String eventId = strScanner.nextLine();
        for (Event event : events) {
            if (event.getId().equals(eventId)) {
                System.out.println("Enter new name:");
                String newName = strScanner.nextLine();
                System.out.println("Enter new date:");
                String newDate = strScanner.nextLine();
                System.out.println("Enter new price:");
                double newPrice = scanner.nextDouble();
                System.out.println("Enter new capacity:");
                int newCapacity = scanner.nextInt();
                System.out.println("Choose status of Event: ");
                Status status = Status.valueOf(strScanner.nextLine().toUpperCase());
                event.setName(newName);
                event.setDate(newDate);
                event.setPrice(newPrice);
                event.setCapacity(newCapacity);
                event.setStatus(status);
                System.out.println("Event edited successfully.");
                return;
            }
        }
        System.out.println("Event not found.");
    }

    private static void addEvent() {
        System.out.println("Enter event name:");
        String name = strScanner.nextLine();
        System.out.println("Enter event date:");
        String date = strScanner.nextLine();
        System.out.println("Enter event price:");
        double price = scanner.nextDouble();
        System.out.println("Enter event capacity:");
        int capacity = scanner.nextInt();
        events.add(new Event(name, date, price, capacity, Status.ACTIVE));
        System.out.println("Event added successfully.");
    }
}

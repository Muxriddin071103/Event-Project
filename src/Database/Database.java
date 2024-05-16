package Database;

import entity.Event;
import entity.Ticket;
import entity.User;
import enum1.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    public static User currentUser;
    public static User currentAdmin;
    public static ArrayList<User> users=new ArrayList<>();
    public static ArrayList<Event> events=new ArrayList<>();
    public static ArrayList<Ticket> tickets=new ArrayList<>();
    public static Scanner scanner=new Scanner(System.in);
    public static Scanner strScanner=new Scanner(System.in);

    static {
        users.add(new User("admin","admin", Role.ADMIN,0.0));
        users.add(new User("user","user", Role.USER,0.0));

        events.add(new Event("standUp","10",5000.0,5, Status.ACTIVE));
        events.add(new Event("Mfactor","10",5000.0,5,Status.NOT_ACTIVE));
        events.add(new Event("foodball","10",1000.0,5,Status.ACTIVE));

    }
}

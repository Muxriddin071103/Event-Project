package database;

import java.util.*;

import enum1.*;
import entity.*;

public class db {
    public static HashSet<User> users = new HashSet<>();
    public static HashSet<String> ids = new HashSet<>();
    public static ArrayList<Book> books = new ArrayList<>();
    public static HashMap<User, List<Book>> userBooks = new HashMap<>();
    public static User currentUser;

    static {
        User admin = new User(generateId(),"admin", "admin", Role.ADMIN,10000.0);
        User user = new User(generateId(),"user", "user", Role.USER,15000.0);
        users.add(admin);
        users.add(user);
        books.addAll(
                Arrays.asList(
                        new Book(generateId(),"Laugh Out Loud: A Comedic Journey",user,BookStatus.NEW,BookStatus.NOT_BORROW, Type.COMEDY,"May 15, 2023"),
                        new Book(generateId(),"Magic Academy: Tales of Enchantment",user,BookStatus.NEW,BookStatus.NOT_BORROW, Type.ACADEMY,"October 15, 2023"),
                        new Book(generateId(),"Forever and Always: A Love Story",user,BookStatus.NEW,BookStatus.NOT_BORROW, Type.ROMANTIC,"November 10, 2023"),
                        new Book(generateId(),"Wizards and Warriors: Academy Adventures",user,BookStatus.NEW,BookStatus.NOT_BORROW, Type.ACADEMY,"December 3, 2023"),
                        new Book(generateId(),"From Startup to Success: Building a Thriving Business",user,BookStatus.NEW,BookStatus.NOT_BORROW, Type.BUSINESS,"March 3, 2023"),
                        new Book(generateId(),"The Mind's Journey: Exploring Human Behavior",user,BookStatus.NEW,BookStatus.NOT_BORROW, Type.PSICHOLOGY,"June 18, 2023")
                )
        );
    }

    public static String generateId(){
        while (true){
            Random random = new Random();
            String id = Integer.toString(random.nextInt(1000, 9999));
            if (ids.add(id))
                return id;
        }
    }

}

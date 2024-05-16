package entity;

import Id.Abs;

import java.util.UUID;

public class Ticket extends Abs {
    private Event event;
    private User user;

    public Ticket() {
    }

    public Ticket( Event event, User user) {

        this.event = event;
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + getId() + '\'' +
                ", event=" + event +
                ", user=" + user +
                '}';
    }

}

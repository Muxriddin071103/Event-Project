package entity;

import Id.Abs;
import enum1.Status;

public class Event extends Abs {
    private String name;
    private String date;
    private Double price;
    private Integer capacity;
    private Status status;

    public Event() {
    }

    public Event(String name, String date, Double price, Integer capacity, Status status) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.capacity = capacity;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", status=" + status +
                '}';
    }
}

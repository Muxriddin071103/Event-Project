package entity;

import enum1.*;

public class Book {
    private String id;
    private String bookName;
    private User owner;
    private BookStatus status;
    private BookStatus borrowing;
    private Type type;
    private String dateOfLease;

    public Book() {
    }

    public Book(String id, String bookName, User owner, BookStatus status,BookStatus borrowing, Type type, String dateOfLease) {
        this.id = id;
        this.bookName = bookName;
        this.owner = owner;
        this.status = status;
        this.borrowing = borrowing;
        this.type = type;
        this.dateOfLease = dateOfLease;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public BookStatus getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(BookStatus borrowing) {
        this.borrowing = borrowing;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDateOfLease() {
        return dateOfLease;
    }

    public void setDateOfLease(String dateOfLease) {
        this.dateOfLease = dateOfLease;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", owner=" + owner +
                ", status=" + status +
                ", borrowing=" + borrowing +
                ", type=" + type +
                ", dateOfLease='" + dateOfLease + '\'' +
                '}';
    }
}

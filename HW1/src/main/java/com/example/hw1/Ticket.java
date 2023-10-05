package com.example.hw1;

import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Ticket {
    private int id;
    @Size(min = 3, max = 50, message = "Мінімальна довжина - 3, максимальнь довжина - 50.")
    private String src;
    @Size(min = 3, max = 50, message = "Мінімальна довжина - 3, максимальнь довжина - 50.")
    private String dest;
    @Size(min = 6, max = 10)
    private String dateTime;
    @TicketPriceConstraint
    private double price;
    private Boolean isBooked;

    public Ticket() {
    }

    public Ticket(int id, String src, String dest, String dateTime, double price, Boolean isBooked) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.dateTime = dateTime;
        this.price = price;
        this.isBooked = isBooked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", dest='" + dest + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", price=" + price +
                ", isBooked=" + isBooked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Double.compare(price, ticket.price) == 0 && Objects.equals(src, ticket.src) && Objects.equals(dest, ticket.dest) && Objects.equals(dateTime, ticket.dateTime) && Objects.equals(isBooked, ticket.isBooked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, src, dest, dateTime, price, isBooked);
    }
}

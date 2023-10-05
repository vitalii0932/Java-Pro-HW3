package com.example.hw1.dao;

import com.example.hw1.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicketDAO {
    private List<Ticket> ticketList = new ArrayList<>();
    {
        ticketList.add(new Ticket(1, "Kyiv", "Chernihiv", "23.09.2023", 198.10, true));
        ticketList.add(new Ticket(2, "Kyiv", "Lviv", "24.09.2023", 300, false));
        ticketList.add(new Ticket(3, "Kyiv", "Chernivtsi", "25.09.2023", 250.50, true));
        ticketList.add(new Ticket(4, "Kyiv", "Kharkiv", "26.09.2023", 288, false));
    }

    public TicketDAO() {
    }

    public void addTicket(Ticket ticket) {
        ticketList.add(ticket);
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    @Override
    public String toString() {
        return "TicketDAO{" +
                "ticketList=" + ticketList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDAO ticketDAO = (TicketDAO) o;
        return Objects.equals(ticketList, ticketDAO.ticketList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketList);
    }
}

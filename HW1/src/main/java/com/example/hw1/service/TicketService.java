package com.example.hw1.service;

import com.example.hw1.Ticket;
import com.example.hw1.dao.TicketDAO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();

    public TicketService() {
    }

    public String getAllTickets() {
        List<Ticket> ticketList = ticketDAO.getTicketList();
        String result = "";
        for (Ticket ticket : ticketList) {
            result += ticket.getSrc() + "," + ticket.getDest() + "," + ticket.getDateTime() + "," + String.valueOf(ticket.getPrice()) + ",";
        }
        String html = getHTML("Всі білети", "", result);
        return html;
    }

    public String getUnBookedTickets() {
        List<Ticket> ticketList = ticketDAO.getTicketList();
        String result = "";
        for (Ticket ticket : ticketList) {
            if(!ticket.getBooked()) {
                result += ticket.getSrc() + "," + ticket.getDest() + "," + ticket.getDateTime() + "," + String.valueOf(ticket.getPrice()) + ",";
            }
        }
        return getHTML("Не заброньовані білети", "", result);
    }

    public String getBookedTickets() {
        List<Ticket> ticketList = ticketDAO.getTicketList();
        String result = "";
        for (Ticket ticket : ticketList) {
            if(ticket.getBooked()) {
                result += ticket.getSrc() + "," + ticket.getDest() + "," + ticket.getDateTime() + "," + String.valueOf(ticket.getPrice()) + ",";
            }
        }
        return getHTML("Заброньовані білети", "", result);
    }

    public String ticketBooking(String src, String dest, String dateTime) {
        List<Ticket> ticketList = ticketDAO.getTicketList();
        String[] result = new String[2];
        result[1] = " , , , ,";
        Boolean check = false;
        for (Ticket ticket : ticketList) {
            if(ticket.getSrc().equals(src) && ticket.getDest().equals(dest) && ticket.getDateTime().equals(dateTime)) {
                check = true;
                if (!ticket.getBooked()) {
                    ticket.setBooked(true);
                    result[0] = "Білет успішно забраньовано";
                    result[1] = ticket.getSrc() + "," + ticket.getDest() + "," + ticket.getDateTime() + "," + String.valueOf(ticket.getPrice()) + ",";
                    return getHTML("Бронювання білетів", result[0], result[1]);
                }
            }
        }
        if(check) {
            result[0] = "Всі білети на даному марштурі заброньовано";
        } else {
            result[0] = "Даний марштур не існує";
        }

        return getHTML("Бронювання білетів", result[0], result[1]);
    }

    public String getHTML(String name, String resp, String data) {
        String[] dataset = data.split(",");
        String result = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name = \"viewport\" content = \"width = device-width, initial-skale=1\">\n" +
                "    \n" +
                "    <title>Tickets</title>\n" +
                "\n" +
                "    <style>\n" +
                "    table {\n" +
                "      width: 100%;\n" +
                "      border-collapse: collapse;\n" +
                "      margin-bottom: 20px;\n" +
                "    }\n" +
                "\n" +
                "    th, td {\n" +
                "      border: 1px solid #dddddd;\n" +
                "      text-align: left;\n" +
                "      padding: 8px;\n" +
                "    }\n" +
                "\n" +
                "    th {\n" +
                "      background-color: #f2f2f2;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n";

        result += String.format("    <h1>%s</h1>\n    <h3>%s</h3>\n", name, resp);
        result += "    <table>\n" +
                "        <thead>\n" +
                "      <tr>\n" +
                "        <th>Source</th>\n" +
                "        <th>Destination</th>\n" +
                "        <th>Date and time</th>\n" +
                "        <th>Price</th>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n";

        String table = "";

        System.out.println(Arrays.toString(dataset));

        for(int i = 0; i < dataset.length; i += 4) {
            table += String.format("<tr>\n" +
                    "        <td>%s</td>\n" +
                    "        <td>%s</td>\n" +
                    "        <td>%s</td>\n" +
                    "        <td>%s</td>\n" +
                    "</tr>\n", dataset[i], dataset[i + 1], dataset[i + 2], dataset[i + 3]);
        }

        result += table + "</tbody>\n" +
                "    </table>\n" +
                "</body>";
        return result;
    }

    @Override
    public String toString() {
        return "TicketService{" +
                "ticketDAO=" + ticketDAO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketService that = (TicketService) o;
        return Objects.equals(ticketDAO, that.ticketDAO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketDAO);
    }
}

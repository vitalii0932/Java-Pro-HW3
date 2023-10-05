package com.example.hw1.controller;

import com.example.hw1.Ticket;
import com.example.hw1.dao.TicketDAO;
import com.example.hw1.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class TicketController {
    private TicketService ticketService = new TicketService();

    public TicketController() {
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<String> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/getUnBookedTickets")
    public ResponseEntity<String> getUnBookedTickets() {
        return new ResponseEntity<>(ticketService.getUnBookedTickets(), HttpStatus.OK);
    }

    @GetMapping("/getBookedTickets")
    public ResponseEntity<String> getBookedTickets() {
        return new ResponseEntity<>(ticketService.getBookedTickets(), HttpStatus.OK);
    }

    @PostMapping("/ticketBooking")
    public ResponseEntity<String> ticketBooking(@RequestBody @Validated Ticket body, Errors errors) {
        if (errors.hasErrors()) {
            List<FieldError> errorsList = errors.getFieldErrors();
            String errorStr = "";
            for (FieldError error : errorsList) {
                errorStr += "Field: " + error.getField() + " | Code: " + error.getCode() + " | DefaultMessage: " + error.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorStr, HttpStatus.OK);
        }

        String respResult = ticketService.ticketBooking(body.getSrc(), body.getDest(), body.getDateTime());
        if(respResult.contains("Білет успішно забраньовано")) {
            return new ResponseEntity<>(respResult, HttpStatus.OK);
        }
        return new ResponseEntity<>(respResult, HttpStatus.ACCEPTED);
    }

    @GetMapping("")
    public ResponseEntity<String> customHeader(@RequestHeader("customHeader") String customHeader, HttpServletRequest request) {
        System.out.println(customHeader + "\n" + request.getHeader("customHeader"));
        System.out.println("hello");
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @PostMapping("/validationTest")
    public String validationTest(@RequestBody @Validated Ticket body, Errors errors) {
        if (errors.hasErrors()) {
            List<FieldError> errorsList = errors.getFieldErrors();
            String errorStr = "";
            for (FieldError error : errorsList) {
                errorStr += "Field: " + error.getField() + " | Code: " + error.getCode() + " | DefaultMessage: " + error.getDefaultMessage() + "\n";
            }
            return errorStr;
        }

        return body.toString();
    }

    @Override
    public String toString() {
        return "TicketController{" +
                "ticketService=" + ticketService +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketController that = (TicketController) o;
        return Objects.equals(ticketService, that.ticketService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketService);
    }
}

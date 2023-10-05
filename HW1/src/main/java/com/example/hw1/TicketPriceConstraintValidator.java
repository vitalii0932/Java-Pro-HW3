package com.example.hw1;

import com.example.hw1.TicketPriceConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TicketPriceConstraintValidator implements ConstraintValidator<TicketPriceConstraint, Double> {
    @Override
    public boolean isValid(Double price, ConstraintValidatorContext constraintValidatorContext) {
        return price > 0;
    }
}

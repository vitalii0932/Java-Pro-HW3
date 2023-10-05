package com.example.hw1;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= TicketPriceConstraintValidator.class)
public @interface TicketPriceConstraint {
    String message() default "Ціна не може бути нижча за нуль";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

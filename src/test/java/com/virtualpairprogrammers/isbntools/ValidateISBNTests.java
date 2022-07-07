package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTests {

    @Test
    public void checkValid10DigitsISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue(result, "first value");
        result = validator.checkISBN("0140177396");
        assertTrue(result, "second value");
    }

    @Test
    public void TenDigitsISBNNumbersEndingWithAnXAreValid(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result, "first value");
    }

    @Test
    public void checkAValid13DigitISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781548742287");
        assertTrue(result);
    }

    @Test
    public void checkInvalid10DigitsISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void checkInvalid13DigitsISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781548742288");
        assertFalse(result);
    }

    @Test
    public void nineDigitsISBNAreNotAllowed(){
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, ()->validator.checkISBN("123456789"));
    }

    @Test
    public void nonNumericISBNsAreNotAllowed(){
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, ()->validator.checkISBN("helloworld"));
    }
}

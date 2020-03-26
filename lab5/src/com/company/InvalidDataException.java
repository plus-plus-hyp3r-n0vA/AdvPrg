package com.company;

public class InvalidDataException extends Exception {
    public InvalidDataException(String err, Exception ex) {
        super(err, ex);
    }
}

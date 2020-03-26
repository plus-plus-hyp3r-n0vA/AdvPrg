package com.company;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String err) {
        super(err);
    }
}

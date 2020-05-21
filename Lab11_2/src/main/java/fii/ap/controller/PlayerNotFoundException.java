package fii.ap.controller;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String msg) {
        super(msg);
    }
}

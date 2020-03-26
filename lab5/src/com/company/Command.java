package com.company;

public abstract class Command {
    String[] args;
    abstract String getName();
    abstract void setArgs(String[] args) throws InvalidCommandException;
    abstract void execute() throws InvalidCommandException, InvalidDataException, ExitShellException;
    abstract void help();
}

package com.company;

public class ExitCommand extends Command {

    @Override
    public String getName() {
        return "exit";
    }

    ExitCommand() {}

    ExitCommand(String ... args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 1) {
            throw new InvalidCommandException("Invalid number of arguments");
        }
    }

    @Override
    void setArgs(String[] args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 1) {
            throw new InvalidCommandException("Invalid number of arguments");
        }
    }

    @Override
    void execute() throws InvalidCommandException, InvalidDataException, ExitShellException {
        throw new ExitShellException("Successful exit!");
    }

    @Override
    void help() {
        System.out.println("Example: exit");
    }
}

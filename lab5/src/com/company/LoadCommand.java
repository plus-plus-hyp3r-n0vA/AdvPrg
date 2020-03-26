package com.company;

public class LoadCommand extends Command {

    @Override
    public String getName(){
        return "load";
    }

    LoadCommand() {}

    LoadCommand(String ... args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 2) {
            throw new InvalidCommandException("Invalid number of arguments");
        }
    }

    void setArgs(String ... args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 2) {
            throw new InvalidCommandException("Invalid number of arguments");
        }
    }

    @Override
    public void execute() throws InvalidDataException {
        try {
            CatalogUtil.loadBinary(args[1]);
        } catch (Exception e)
        {
            throw new InvalidDataException("Invalid path or URL", e);
        }
    }

    @Override
    void help() {
        System.out.println("Example: load catalog_path");
    }
}

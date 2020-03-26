package com.company;

public class ViewCommand extends Command {

    @Override
    public String getName(){
        return "view";
    }

    ViewCommand() {}

    ViewCommand(String ... args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 3)
            throw new InvalidCommandException("Invalid number of arguments");
    }

    @Override
    void setArgs(String[] args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 3)
            throw new InvalidCommandException("Invalid number of arguments");
    }

    @Override
    public void execute() throws InvalidDataException {
        try {
            Catalog c = CatalogUtil.loadBinary(args[2]);
            CatalogUtil.view(c.findByName(args[1]));
        } catch (Exception e)
        {
            throw new InvalidDataException("Invalid path, URL or document_name", e);
        }
    }

    @Override
    void help() {
        System.out.println("Example: view document_name catalog_path");
    }
}

package com.company;

public class ListCommand extends Command{
    @Override
    public String getName(){
        return "list_documents";
    }

    ListCommand() {}

    ListCommand(String ... args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 2)
            throw new InvalidCommandException("Invalid number of arguments");
    }

    @Override
    void setArgs(String[] args) throws InvalidCommandException {
        this.args = args;
        if(args.length != 2)
            throw new InvalidCommandException("Invalid number of arguments");
    }

    @Override
    public void execute() throws InvalidDataException {
        try {
            Catalog c = CatalogUtil.loadBinary(args[1]);
            System.out.println("Documents:");
            for(Document d : c.getDocuments())
                System.out.println(d.getName());
        } catch (Exception e)
        {
            throw new InvalidDataException("Invalid path, URL or document_name", e);
        }
    }

    @Override
    void help() {
        System.out.println("Example: list_documents catalog_path");
    }
}

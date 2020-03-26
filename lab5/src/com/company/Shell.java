package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shell {
    Command[] commands;

    public Shell() {
        commands = new Command[]{new LoadCommand(), new ListCommand(), new ViewCommand(), new ExitCommand()};
    }

    private String[] Split(String command) {
        Pattern matcherPattern = Pattern.compile("(?<=\")([^\"]*)(?=\")|[^ \"]*");
        Matcher matcher = matcherPattern.matcher(command);
        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            if (!matcher.group().equals(""))
                tokens.add(matcher.group());
        }
        String[] res = new String[tokens.size()];
        res = tokens.toArray(res);
        return res;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        infinite_loop:
        while (true) {
            System.out.print("comm> ");
            String command = scanner.nextLine();
            String[] args = Split(command);
            boolean command_found = false;

            for (Command c : commands) {
                if (c.getName().equals(args[0])) {
                    command_found = true;
                    try {
                        c.setArgs(args);
                        c.execute();
                    } catch (InvalidCommandException | InvalidDataException e) {
                        System.out.println(e.getMessage());
                        break;
                    } catch (ExitShellException e) {
                        System.out.println(e.getMessage());
                        break infinite_loop;
                    }
                }
            }
            if (!command_found) {
                System.out.println("Invalid command!\nAvailable commands:");
                for (Command c : commands) {
                    System.out.println("Command: " + c.getName());
                    c.help();
                }
            }
        }
    }
}

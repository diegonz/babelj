package com.pragmabits.notify;

import java.io.IOException;

/**
 * The type Linux exec mock.
 */
public class LinuxExecMock extends RuntimeExecMock {
    /**
     * Fake Exec process.
     *
     * @param command the command
     * @throws IOException the io exception
     */
    @Override
    public Process exec(String[] command) throws IOException {
        boolean commandOk = true;
        if (!command[0].equals("notify-send"))
            commandOk = false;
        if (!command[1].equals("-i") && !command[1].equals("--help"))
            commandOk = false;
        if (!command[2].equals("dialog-information") && !command[2].equals(">"))
            commandOk = false;
        return Runtime.getRuntime().exec(commandOk ? "echo" : "ls --wrong");
    }

    /**
     * Fake Exec process.
     *
     * @param command the command
     * @throws IOException the io exception
     */
    @Override
    public Process exec(String command) throws IOException {
        String[] commandArray = command.split("\\s+");
        boolean commandOk = true;
        if (!commandArray[0].equals("notify-send"))
            commandOk = false;
        if (!commandArray[1].equals("-i") && !commandArray[1].equals("--help"))
            commandOk = false;
        if (!commandArray[2].equals("dialog-information") && !commandArray[2].equals(">"))
            commandOk = false;
        return Runtime.getRuntime().exec(commandOk ? "echo" : "ls --wrong");
    }
}

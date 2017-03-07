package com.pragmabits.notify;

import java.io.IOException;

public class MacOsXExecMock extends RuntimeExecMock {

    private static final String CLI_NOTIFY_APP = "/Applications/terminal-notifier.app/Contents/MacOS/terminal-notifier";

    /**
     * Fake Exec process.
     *
     * @param command the command
     * @throws IOException the io exception
     */
    @Override
    public Process exec(String[] command) throws IOException {
        boolean commandOk = true;
        if (!command[0].equals(CLI_NOTIFY_APP))
            commandOk = false;
        if (!command[1].equals("-title") && !command[1].equals(">"))
            commandOk = false;
        if (!command[2].contains("ℹ ") && !command[2].equals("/dev/null"))
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
        if (!commandArray[0].equals(CLI_NOTIFY_APP))
            commandOk = false;
        if (!commandArray[1].equals("-title") && !commandArray[1].equals(">"))
            commandOk = false;
        if (!commandArray[2].contains("ℹ ") && !commandArray[2].equals("/dev/null"))
            commandOk = false;
        return Runtime.getRuntime().exec(commandOk ? "echo" : "ls --wrong");
    }
}

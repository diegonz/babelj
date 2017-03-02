package com.pragmabits.babelj.notify;

import java.io.IOException;

/**
 * The type Runtime exec.
 */
public class RuntimeExec {
    /**
     * Exec process.
     *
     * @param command the command
     * @throws IOException the io exception
     */
    public Process exec(String[] command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }

    /**
     * Exec process.
     *
     * @param command the command
     * @throws IOException the io exception
     */
    public Process exec(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }
}

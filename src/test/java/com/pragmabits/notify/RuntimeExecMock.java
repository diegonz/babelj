package com.pragmabits.notify;

import java.io.IOException;

/**
 * The type Runtime exec mock.
 */
public abstract class RuntimeExecMock extends RuntimeExec {
    /**
     * Fake Exec process.
     *
     * @param command the command
     * @throws IOException the io exception
     */
    @Override
    public abstract Process exec(String[] command) throws IOException;

    /**
     * Fake Exec process.
     *
     * @param command the command
     * @throws IOException the io exception
     */
    @Override
    public abstract Process exec(String command) throws IOException;
}

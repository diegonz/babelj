package com.pragmabits.babelj.utils;

import com.pragmabits.babelj.BabelJException;

public class CliArgsError extends BabelJException {
    /**
     * Instantiates a new Utils exception.
     *
     * @param message the message
     */
    CliArgsError(String message) {
        super(message);
    }

    /**
     * Instantiates a new Utils exception.
     *
     * @param cause the cause
     */
    CliArgsError(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Utils exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    CliArgsError(String message, Throwable cause) {
        super(message, cause);
    }
}

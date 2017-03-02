package com.pragmabits.babelj;

/**
 * Exception BabelJException
 *
 * @author Diego González
 */
public abstract class BabelJException extends Exception {
    /**
     * Instantiates a new Utils exception.
     *
     * @param message the message
     */
    public BabelJException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Utils exception.
     *
     * @param cause the cause
     */
    public BabelJException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Utils exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BabelJException(String message, Throwable cause) {
        super(message, cause);
    }
}

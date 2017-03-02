package com.pragmabits.babelj.notify;

import com.pragmabits.babelj.BabelJException;

/**
 * Exception NotifyException
 *
 * @author Diego González
 */
public class NotifyException extends BabelJException {

    /**
     * Instantiates a new Notify exception.
     *
     * @param message the message
     */
    NotifyException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Notify exception.
     *
     * @param cause the cause
     */
    NotifyException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Notify exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    NotifyException(String message, Throwable cause) {
        super(message, cause);
    }
}

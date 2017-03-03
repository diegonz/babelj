package com.pragmabits.babelj.notify;

import com.pragmabits.babelj.BabelJException;

/**
 * Exception NotifyError
 *
 * @author Diego González
 */
public class NotifyError extends BabelJException {

    /**
     * Instantiates a new Notify exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    NotifyError(String message, Throwable cause) {
        super(message, cause);
    }
}

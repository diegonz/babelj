package com.pragmabits.notify;

import com.pragmabits.BabelJException;

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

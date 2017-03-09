package com.pragmabits.utils;

import com.pragmabits.BabelJException;

/**
 * Exception ClipboardManagerError
 *
 * @author Diego González
 */
public class ClipboardManagerError extends BabelJException {
    
    /**
     * Instantiates a new Input error.
     *
     * @param message the message
     * @param cause   the cause
     */
    ClipboardManagerError(String message, Exception cause) {
        super(message, cause);
    }
}

package com.pragmabits.babelj.utils;

import com.pragmabits.babelj.BabelJException;

/**
 * Exception ClipboardError
 *
 * @author Diego González
 */
public class ClipboardError extends BabelJException {
    
    /**
     * Instantiates a new Input error.
     *
     * @param message the message
     * @param cause   the cause
     */
    ClipboardError(String message, Exception cause) {
        super(message, cause);
    }
}

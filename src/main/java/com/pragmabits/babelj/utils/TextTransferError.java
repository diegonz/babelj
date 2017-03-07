package com.pragmabits.babelj.utils;

import com.pragmabits.babelj.BabelJException;

/**
 * Exception TextTransferError
 *
 * @author Diego González
 */
public class TextTransferError extends BabelJException {
    
    /**
     * Instantiates a new Input error.
     *
     * @param message the message
     * @param cause   the cause
     */
    TextTransferError(String message, Exception cause) {
        super(message, cause);
    }
}

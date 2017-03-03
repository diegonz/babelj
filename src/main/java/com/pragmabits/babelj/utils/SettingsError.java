package com.pragmabits.babelj.utils;

import com.pragmabits.babelj.BabelJException;

/**
 * Exception SettingsError
 *
 * @author Diego González
 */
public class SettingsError extends BabelJException {

    /**
     * The enum ErrorCode.
     */
    public enum Error {
        /**
         * The Backend.
         */
        BACKEND("Error found while parsing default backend."),
        /**
         * The Backend id.
         */
        BACKEND_ID("Error found while parsing your API ID(s)."),
        /**
         * The Language.
         */
        LANGUAGE("Error found while parsing default language."),
        /**
         * The Input.
         */
        INPUT("Error found while parsing default input."),
        /**
         * The Output.
         */
        OUTPUT("Error found while parsing default output."),
        /**
         * The Unknown error.
         */
        UNKNOWN_ERROR("An unknown error occurred while parsing config.");

        private String errorDescription;

        Error(String errorDescription) {
            this.errorDescription = errorDescription;
        }

        /**
         * Gets error description.
         *
         * @return the error description
         */
        public String getErrorDescription() {
            return this.errorDescription;
        }
    }

    private final Error error;
    private final String errorCauseItem;

    /**
     * Constructs a new exception with the specified ErrorCode. The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param error the error enum type. The detail message is saved for
     *              later retrieval by the {@link #getErrorDescription()} method.
     */
    SettingsError(Error error) {
        super(error.errorDescription);
        this.error = error;
        this.errorCauseItem = null;
    }

    /**
     * Gets error causing item.
     *
     * @return the error causing item
     */
    public String getErrorCauseItem() {
        return this.errorCauseItem;
    }

    /**
     * Gets error description.
     *
     * @return the error description
     */
    public String getErrorDescription() {
        return this.error.getErrorDescription();
    }
}

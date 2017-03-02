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
        BACKEND("ErrorCode found while parsing default backend."),
        /**
         * The Backend id.
         */
        BACKEND_ID("ErrorCode found while parsing your API ID(s)."),
        /**
         * The Language.
         */
        LANGUAGE("ErrorCode found while parsing default language."),
        /**
         * The Input.
         */
        INPUT("ErrorCode found while parsing default input."),
        /**
         * The Output.
         */
        OUTPUT("ErrorCode found while parsing default output."),
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
    private final String errorCausingItem;

    /**
     * Constructs a new exception of Unknown type. The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     */
    SettingsError() {
        super(Error.UNKNOWN_ERROR.errorDescription);
        this.error = Error.UNKNOWN_ERROR;
        this.errorCausingItem = null;
    }

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
        this.errorCausingItem = null;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @param error the detail message. The detail message is saved for
     *              later retrieval by the {@link #getErrorDescription()} method.
     * @since 1.4
     */
    SettingsError(Throwable cause, Error error) {
        super(error.getErrorDescription(), cause);
        this.error = error;
        this.errorCausingItem = null;
    }

    /**
     * Constructs a new exception with the specified ErrorCode and errorCausingItem. The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param error            the detail message. The detail message is saved for
     *                         later retrieval by the {@link #getErrorDescription()} method.
     * @param errorCausingItem the detail message. The detail message is saved for
     *                         later retrieval by the {@link #getErrorCausingItem()} ()} method.
     */
    SettingsError(Error error, String errorCausingItem) {
        super(error.errorDescription);
        this.error = error;
        this.errorCausingItem = errorCausingItem;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @param error   the detail message. The detail message is saved for
     *                later retrieval by the {@link #getErrorDescription()} method.
     * @since 1.4
     */
    SettingsError(String message, Throwable cause, Error error) {
        super(message, cause);
        this.error = error;
        this.errorCausingItem = null;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables.
     *
     * @param cause            the cause (which is saved for later retrieval by the
     *                         {@link #getCause()} method).  (A <tt>null</tt> value is
     *                         permitted, and indicates that the cause is nonexistent or
     *                         unknown.)
     * @param error            the detail message. The detail message is saved for
     *                         later retrieval by the {@link #getErrorDescription()} method.
     * @param errorCausingItem the detail message. The detail message is saved for
     *                         later retrieval by the {@link #getErrorCausingItem()} ()} method.
     * @since 1.4
     */
    SettingsError(Throwable cause, Error error, String errorCausingItem) {
        super(cause);
        this.error = error;
        this.errorCausingItem = errorCausingItem;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message          the detail message (which is saved for later retrieval
     *                         by the {@link #getMessage()} method).
     * @param cause            the cause (which is saved for later retrieval by the
     *                         {@link #getCause()} method).  (A <tt>null</tt> value is
     *                         permitted, and indicates that the cause is nonexistent or
     *                         unknown.)
     * @param error            the detail message. The detail message is saved for
     *                         later retrieval by the {@link #getErrorDescription()} method.
     * @param errorCausingItem the detail message. The detail message is saved for
     *                         later retrieval by the {@link #getErrorCausingItem()} ()} method.
     * @since 1.4
     */
    SettingsError(String message, Throwable cause, Error error, String errorCausingItem) {
        super(message, cause);
        this.error = error;
        this.errorCausingItem = errorCausingItem;
    }

    /**
     * Gets error enum.
     *
     * @return the error
     */
    public Error getError() {
        return this.error;
    }

    /**
     * Gets error causing item.
     *
     * @return the error causing item
     */
    public String getErrorCausingItem() {
        return this.errorCausingItem;
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

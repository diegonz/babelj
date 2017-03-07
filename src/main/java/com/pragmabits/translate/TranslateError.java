package com.pragmabits.translate;

import com.pragmabits.BabelJException;

/**
 * Exception TranslateError
 *
 * @author Diego González
 */
public class TranslateError extends BabelJException {

    /**
     * The enum ErrorCode.
     */
    public enum ErrorCode {
        /**
         * E 401 errorCode.
         */
        E401(401, "ERR_KEY_INVALID"),
        /**
         * E 402 errorCode.
         */
        E402(402, "ERR_KEY_BLOCKED"),
        /**
         * E 403 errorCode.
         */
        E403(403, "ERR_DAILY_REQ_LIMIT_EXCEEDED"),
        /**
         * E 404 errorCode.
         */
        E404(404, "ERR_DAILY_CHAR_LIMIT_EXCEEDED"),
        /**
         * E 405 errorCode.
         */
        E405(405, "ERR_TRANSLATION_NOT_AVAILABLE"),
        /**
         * E 406 errorCode.
         */
        E406(406, "ERR_SAME_LANGUAGE_SOURCE_AND_TARGET"),
        /**
         * E 413 errorCode.
         */
        E413(413, "ERR_TEXT_TOO_LONG"),
        /**
         * E 422 errorCode.
         */
        E422(422, "ERR_TEXT_NOT_PROCESSABLE"),
        /**
         * E 501 errorCode.
         */
        E501(501, "ERR_LANG_NOT_SUPPORTED"),
        /**
         * E 503 errorCode.
         */
        E503(503, "ERR_SERVICE_NOT_AVAILABLE"),
        /**
         * E 505 errorCode.
         */
        E505(505, "ERR_LANGUAGE_NOT_AVAILABLE"),
        /**
         * E 666 errorCode.
         */
        E666(666, "ERR_UNDEFINED_ERROR");

        private int intErrorCode;
        private String errorDescription;

        ErrorCode(int intErrorCode, String errorDescription) {
            this.intErrorCode = intErrorCode;
            this.errorDescription = errorDescription;
        }

        /**
         * From int errorCode.
         *
         * @param intErrorCode the int errorCode code
         * @return the errorCode
         */
        public static ErrorCode fromInt(final int intErrorCode) {
            for (ErrorCode errorCode : ErrorCode.values()) {
                if (errorCode.getIntErrorCode() == intErrorCode) {
                    return errorCode;
                }
            }
            return ErrorCode.E666;
        }

        /**
         * Gets int errorCode code.
         *
         * @return the int errorCode code
         */
        public int getIntErrorCode() {
            return this.intErrorCode;
        }

        /**
         * Gets errorCode description.
         *
         * @return the errorCode description
         */
        public String getErrorDescription() {
            return this.errorDescription;
        }
    }

    private final ErrorCode errorCode;

    /**
     * Instantiates a new Translate exception.
     *
     * @param errorCode the errorCode
     */
    public TranslateError(ErrorCode errorCode) {
        super(errorCode.getErrorDescription());
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new Translate exception.
     *
     * @param errorCode the errorCode
     * @param cause the cause
     */
    public TranslateError(ErrorCode errorCode, Exception cause) {
        super(errorCode.getErrorDescription(), cause);
        this.errorCode = errorCode;
    }

    /**
     * Gets errorCode description.
     *
     * @return the errorCode description
     */
    public String getErrorDescription() {
        return this.errorCode.getErrorDescription();
    }
}

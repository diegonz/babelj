package com.pragmabits.translate;

/**
 * Translator Interface
 *
 * @author Diego González
 */
@FunctionalInterface public interface Translator {

    /**
     * Translate translation.
     *
     * @param request the request
     * @return the translation
     * @throws TranslateError the translate exception
     */
    Translation translate(Translation request) throws TranslateError;
}

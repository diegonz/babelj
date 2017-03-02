package com.pragmabits.babelj.translate;

/**
 * Translator Interface
 *
 * @author Diego González
 */
public interface Translator {

    /**
     * Check translation available boolean.
     *
     * @param request the request
     * @return the boolean representing translation availability
     * @throws TranslateError the translate exception
     */
    boolean checkTranslationAvailable(Translation request) throws TranslateError;

    /**
     * Translate translation.
     *
     * @param request the request
     * @return the translation
     * @throws TranslateError the translate exception
     */
    Translation translate(Translation request) throws TranslateError;
}

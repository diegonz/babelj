package com.pragmabits.translate;

/**
 * The type Translation.
 */
public class Translation {
    private String sourceLang;
    private String targetLang;
    private String text;

    /**
     * Instantiates a new Translation.
     *
     * @param targetLang the targetLang
     * @param text     the text
     */
    public Translation(String targetLang, String text) {
        this.targetLang = targetLang;
        this.text = text;
    }

    /**
     * Instantiates a new Translation.
     *
     * @param sourceLang the source lang
     * @param targetLang the target lang
     * @param text       the text
     */
    public Translation(String sourceLang, String targetLang, String text) {
        this.sourceLang = sourceLang != null ? sourceLang : "auto";
        this.targetLang = targetLang;
        this.text = text;
    }

    /**
     * Gets the source language.
     *
     * @return the source lang
     */
    public String getSourceLang() {
        return sourceLang;
    }

    /**
     * Gets the target language.
     *
     * @return the targetLang
     */
    public String getTargetLang() {
        return targetLang;
    }

    /**
     * Gets the translated text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }
}

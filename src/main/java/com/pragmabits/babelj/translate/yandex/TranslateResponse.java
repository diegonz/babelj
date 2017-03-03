package com.pragmabits.babelj.translate.yandex;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Translate response.
 */
class TranslateResponse {

    @SerializedName("text")
    @Expose
    private List<String> text = null;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("code")
    @Expose
    private Integer code;

    /**
     * Instantiates a new Translate response.
     *
     * @param text List<String> containing translated text
     * @param lang String Translation performed
     * @param code Integer response status code
     */
    TranslateResponse(List<String> text, String lang, Integer code) {
        super();
        this.text = text;
        this.lang = lang;
        this.code = code;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    List<String> getText() {
        return text;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    String getLang() {
        return lang;
    }

    /**
     * Gets return code.
     *
     * @return the code
     */
    Integer getCode() {
        return code;
    }
}
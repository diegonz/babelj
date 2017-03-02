package com.pragmabits.babelj.translate.yandex;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Translate response.
 */
public class TranslateResponse {

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
    public List<String> getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    void setText(List<String> text) {
        this.text = text;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets language.
     *
     * @param lang the language
     */
    void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Gets return code.
     *
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets return code.
     *
     * @param code the code
     */
    void setCode(Integer code) {
        this.code = code;
    }
}
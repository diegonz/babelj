package com.pragmabits.babelj.translate.yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Detect response.
 */
class DetectResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("lang")
    @Expose
    private String lang;

    /**
     * Instantiates a new Detect response.
     *
     * @param code the code
     * @param lang the lang
     */
    DetectResponse(Integer code, String lang) {
        this.code = code;
        this.lang = lang;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    Integer getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets lang.
     *
     * @return the lang
     */
    String getLang() {
        return lang;
    }

    /**
     * Sets lang.
     *
     * @param lang the lang
     */
    void setLang(String lang) {
        this.lang = lang;
    }
}
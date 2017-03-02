package com.pragmabits.babelj.translate.yandex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Get langs response.
 */
class GetLangsResponse {

    @SerializedName("dirs")
    @Expose
    private ArrayList<String> dirs = null;
    @SerializedName("langs")
    @Expose
    private HashMap<String, String> langs = null;

    /**
     * Instantiates a new Get langs response.
     *
     * @param dirs  the dirs
     * @param langs the langs
     */
    GetLangsResponse(List<String> dirs, Map<String, String> langs) {
        this.dirs = (ArrayList<String>) dirs;
        this.langs = (HashMap<String, String>) langs;
    }

    /**
     * Gets dirs.
     *
     * @return the dirs
     */
    ArrayList<String> getDirs() {
        return dirs;
    }

    /**
     * Sets dirs.
     *
     * @param dirs the dirs
     */
    void setDirs(ArrayList<String> dirs) {
        this.dirs = dirs;
    }

    /**
     * Gets langs.
     *
     * @return the langs
     */
    HashMap<String, String> getLangs() {
        return langs;
    }

    /**
     * Sets langs.
     *
     * @param langs the langs
     */
    void setLangs(HashMap<String, String> langs) {
        this.langs = langs;
    }
}
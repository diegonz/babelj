package com.pragmabits.translate.yandex;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * YandexTranslateService Retrofit2 Interface
 *
 * @author Diego González
 */
@FunctionalInterface public interface YandexTranslateService {

    /**
     * Translate call.
     *
     * @param apiVersion         the api version
     * @param apiKey             the api key
     * @param translateDirection the translate direction
     * @param textFormat         the text format
     * @param targetText         the target text
     * @return the call
     */
    @POST("api/{apiVersion}/tr.json/translate")
    Call<TranslateResponse> translate(@Path("apiVersion") String apiVersion,
                                      @Query("key") String apiKey,
                                      @Query("lang") String translateDirection,
                                      @Query("format") String textFormat,
                                      @Query("text") String targetText);
}

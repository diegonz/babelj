package com.pragmabits.babelj.translate.yandex;

import com.pragmabits.babelj.translate.TranslateError;
import com.pragmabits.babelj.translate.Translation;
import com.pragmabits.babelj.translate.Translator;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * YandexTranslator Class - Handles translation on yandex backend.
 *
 * @author Diego González
 */
public class YandexTranslator implements Translator {

    private static final String API_VERSION = "v1.5";
    private static final String BASE_API_URL = "https://translate.yandex.net/";
    private String apiKey;

    private YandexTranslateService service;

    /**
     * Instantiates a new Yandex translator.
     *
     * @param apiKey the api key
     */
    public YandexTranslator(String apiKey) {
        this.apiKey = apiKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.service = retrofit.create(YandexTranslateService.class);
    }

    /**
     * Translate translation.
     *
     * @param request the request
     * @return the translation
     * @throws TranslateError the translate exception
     */
    @Override
    public Translation translate(Translation request) throws TranslateError {
        String translationDirection = "auto".equals(request.getSourceLang())
                ? request.getTargetLang()
                : request.getSourceLang() + "-" + request.getTargetLang();
        Call<TranslateResponse> translateCall = this.service.translate(
                API_VERSION, this.apiKey, translationDirection, "plain", request.getText());
        try {
            TranslateResponse translateResponse = translateCall.execute().body();
            if (translateResponse.getCode() == 200) {
                String[] langs = translateResponse.getLang().split("-");
                return new Translation(langs[0], langs[1], translateResponse.getText().get(0));
            }
            throw new TranslateError(TranslateError.ErrorCode.fromInt(translateResponse.getCode()));
        } catch (IOException e) {
            throw new TranslateError(TranslateError.ErrorCode.E503, e);
        }
    }

}

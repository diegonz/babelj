package com.pragmabits.translate.yandex;

import com.pragmabits.translate.Translation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("SpellCheckingInspection")
public class YandexTranslatorTest {

    private static final String apiKey = "trnsl.1.1.20170203T202734Z.5093fb80fddda46a.b66dd1bf1599f71e29ca9f3afe38d9a5ac9fc216";
    private static final String sourceLang = "auto";
    private static final String sourceText = "Hello world";
    private static final String targetLang = "es";
    private static final String targetText = "Hola mundo";
    private YandexTranslator translator;

    @Before
    public void setUp() throws Exception {
        this.translator = new YandexTranslator(apiKey);
    }

    @Test
    public void translate() throws Exception {
        // TODO - Mock yandex backend
        Translation translation = translator.translate(new Translation(sourceLang, targetLang, sourceText));
        assertEquals("YandexTranslator (net)translate", targetText, translation.getText());
    }

}
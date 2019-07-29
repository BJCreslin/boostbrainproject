package ru.bjcreslin.domain.apiobjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Портал открытых данных Правительства Москвы
 * https://apidata.mos.ru/
 */

public class MosRuDataServer extends DataObiect {
    private static final String APIKEY = "586d058a1a8ef94f0cd1105d4c0550e9";
    private static final String WEBADRESS = "https://apidata.mos.ru/";
    private static final String VERSIONAPI = "1";

    /**
     * Возвращает част вебадреса с АПИКЕЙ
     * @return String
     */
     String getApikey() {
        return "?api_key=" + APIKEY;
    }

    /**
     * Возвращает версию API
     *
     * @return String в JSON с текущей версией API
     */
    @Override
    public String getApiversion() {
        String address = WEBADRESS + "version";
        String x = getPageFromUrl(address);
        if (x != null) return x;
        return VERSIONAPI;
    }

    /**
     * Функция получения страницы по адрессу
     *
     * @param adress -web adress
     * @return Веб страницу в виде текста
     */
    @Override
    public String getPageFromUrl(String adress) {
        StringBuilder text = new StringBuilder();
        try {
            URL url = new URL(adress);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null)
                    break;
                text.append(line);
            }

        } catch (IOException e) {
            return "Error Connection";
        }
        return text.toString();
    }
}

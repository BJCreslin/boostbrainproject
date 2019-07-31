package ru.bjcreslin.domain.apiobjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Портал открытых данных Правительства Москвы
 * https://apidata.mos.ru/
 */
@Log
@Component
public class MosRuDataServer extends DataObiect {
    private static final String APIKEY = "586d058a1a8ef94f0cd1105d4c0550e9";
    private static final String WEBADRESS = "https://apidata.mos.ru/";
    private static final String VERSIONAPI = "v1";

    /**
     * Возвращает часть вебадреса с АПИКЕЙ
     *
     * @return String
     */
    String getApikey() {
        return "api_key=" + APIKEY;
    }

    /**
     * Возвращает версию API
     *
     * @return String в JSON с текущей версией API
     */
    @Override
    public APIVersion getApiversion() throws ErrorConectionToMosRuServer, ErrorApiVersionCheck {
        String address = WEBADRESS + "version";
        String x = getPageFromUrl(address);
        ObjectMapper mapper = new ObjectMapper();
        APIVersion apiVersion;
        try {
            apiVersion = mapper.readValue(x, APIVersion.class);
        } catch (IOException e) {
            throw new ErrorApiVersionCheck(e.getMessage());
        }
        return apiVersion;
    }

    /**
     * Функция получения страницы по адрессу
     *
     * @param adress -web adress
     * @return Веб страницу в виде текста
     */


    @Override
    public String getPageFromUrl(String adress) throws ErrorConectionToMosRuServer {
        StringBuilder text = new StringBuilder();
        try {
            URL url = new URL(adress);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null)
                    break;
                text.append(line);
            }
        } catch (IOException e) {
            log.severe("Error Connection in getPageFromUrl");
            throw new ErrorConectionToMosRuServer(e.getMessage());
        }
        return text.toString();
    }

    /**
     * генерирует адрес запроса
     * @param adressPart- часть запроса между адресом сервера , версиейAPI, datasets  и ключом
     * @return полный адрес запроса в виде строки
     */
    @Override
    public String generatedAdress(String adressPart) {
        String separationCharacter = (adressPart.contains("?")) ? "&" : "?";
        String result = WEBADRESS + VERSIONAPI + "/datasets/" + adressPart + separationCharacter + getApikey();
        log.info(result);
        return result;
    }

    /**
     * Получение версии набора данных с сервера.
     * @param idDataSets  // "Id": 61321,  - ID данных по тренерам
     * @return
     * @throws ErrorConectionToMosRuServer
     * @throws ErrorApiVersionCheck
     */
    @Override
    public DataSetsVersion getDataSetsVersion(int idDataSets) throws ErrorConectionToMosRuServer, ErrorApiVersionCheck {
        String adress = generatedAdress(idDataSets +"/version");
        String textWithAnswer = getPageFromUrl(adress);
        ObjectMapper mapper = new ObjectMapper();
        DataSetsVersion dataSetsVersion;

        try {
            dataSetsVersion = mapper.readValue(textWithAnswer, DataSetsVersion.class);
        } catch (IOException e) {
            throw new ErrorApiVersionCheck(e.getMessage());
        }
        return dataSetsVersion;
    }
}

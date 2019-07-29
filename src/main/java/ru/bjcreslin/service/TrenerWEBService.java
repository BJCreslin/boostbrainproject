package ru.bjcreslin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import ru.bjcreslin.domain.apiobjects.MosRuDataServer;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log
public class TrenerWEBService implements WebService {

    private static final String ID_DATA_GROUPE = "61321"; // "Id": 61321,  - ID данных по тренерам

    public TrenerWEBService(MosRuDataServer dataServer) {
        this.dataServer = dataServer;
    }

    MosRuDataServer dataServer;


    @Override
    public int getCount() {

        String urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/count");
        String count = dataServer.getPageFromUrl(urlForReceice);
        return Integer.parseInt(count);
    }

    @Override
    public List<JSONWrapperObject> getAll() {
        String txt = getTextJSONData();
        return textToArrayOfJsonConverter(txt);
    }

    @Override
    public List<JSONWrapperObject> textToArrayOfJsonConverter(String txt) {
        List<JSONWrapperObject> resultList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            resultList = Arrays.asList(mapper.readValue(txt, JSONWrapperObject[].class));
            System.out.println(resultList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public String getTextJSONData() {
        int countAll = getCount();
        StringBuilder txt = new StringBuilder();
        /*
        Внимание: при запросе датасетов с количеством записей более 10000шт., в ответе будет передан статус 413.
        Получение таких датасетов возможно с применением описанных ниже параметров $top (максимальное допустимое значение - 500шт.) и $skip.
         */
        if (countAll > 9999) {
            for (int i = 1; i <= countAll; i += 500) {
                String urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/rows" + "?$top=" + 500 + "&$skip=" + i);
                log.info(urlForReceice);
                txt.append(dataServer.getPageFromUrl(urlForReceice));
            }
        } else {
            String urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/rows" + "?$top=" + 1);
            log.info(urlForReceice);
            txt.append(dataServer.getPageFromUrl(urlForReceice));
        }
        return txt.toString();
    }

}

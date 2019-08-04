package ru.bjcreslin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.jsonobjects.JSONTrenerObject;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для методов преобразования объектов
 */
@Log
@Service
public class ObjectConversionService {

    /**
     * Превращает JSONTrenerObject в объект Trener
     *
     * @param jsonTrenerObject JSONTrenerObject объект
     * @return Объект Trener
     */
    Trener jSONTrenerObjectToTrener(JSONTrenerObject jsonTrenerObject) {
        Trener trener = new Trener();
        trener.setName(jsonTrenerObject.getName());
        trener.setLastName(jsonTrenerObject.getLastName());
        trener.setMiddleName(jsonTrenerObject.getMiddleName());
        trener.setGender(jsonTrenerObject.getGender());
        jsonTrenerObject.getAcademicDegree().forEach(x -> trener.getAcademicDegree().add((String) x));
        jsonTrenerObject.getSport().forEach(x -> trener.getSport().add(x));
        jsonTrenerObject.getEducation().forEach(x -> trener.getEducation().add((String) x));
        return trener;
    }

    /**
     * Превразщает JSONWrapperObject (основной объект данных мосдата) в JSONTrenerObject
     *
     * @param jsonWrapperObject объект
     * @return JSONTrenerObject
     */
    static JSONTrenerObject wrapperToTrener(JSONWrapperObject jsonWrapperObject) {
        return jsonWrapperObject.getCells();
    }

    /**
     * Делает из Лист JSONWrapperObject Лист объектов trener
     *
     * @param wrapperObjectList List JSONWrapperObject
     * @return List Trener
     */
    List<Trener> listJSONWrapperToTrenerList(List<JSONWrapperObject> wrapperObjectList) {
        return wrapperObjectList.stream().map(ObjectConversionService::wrapperToTrener).map(this::jSONTrenerObjectToTrener).
                collect(Collectors.toList());
    }

    /**
     * Метод делающий из текста с JSON данными лист объектов  JSONWrapperObject
     *
     * @param txt данные с сервера Мосдата
     * @return Коллекция с данными JSONWrapperObject
     * @throws ErrorParsingTxtJsonToPojo ошибка перобразования
     */
    public List<JSONWrapperObject> textToArrayOfJsonConverter(String txt) throws ErrorParsingTxtJsonToPojo {
        List<JSONWrapperObject> resultList;
        ObjectMapper mapper = new ObjectMapper();
        try {
            resultList = Arrays.asList(mapper.readValue(txt, JSONWrapperObject[].class));
        } catch (IOException e) {
            log.severe("не удалось распарсить данные в textToArrayOfJsonConverter");
            log.severe(e.getMessage());
            throw new ErrorParsingTxtJsonToPojo(e.getMessage());
        }
        log.info("количество полученных данных " + resultList.size());
        return resultList;
    }

    /**
     * Делает из коллекции JSONWrapperObject  текст с JSON дангными
     *
     * @param objectList Коллекция из JSONWrapperObject
     * @return текст с JSON данными
     */
    public String wrapperObjectListToString(List<JSONWrapperObject> objectList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(objectList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для методов преобразования объектов
 */
@Log
@Service
public class ObjectConversionService {

    public Trener jSONTrenerObjectToTrener(JSONTrenerObject jsonTrenerObject) {

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

    static JSONTrenerObject wrapperToTrener(JSONWrapperObject jsonWrapperObject) {
        return jsonWrapperObject.getCells();
    }

    public List<Trener> listJSONWrapperToTrenerList(List<JSONWrapperObject> wrapperObjectList) {
        return wrapperObjectList.stream().map(ObjectConversionService::wrapperToTrener).map(this::jSONTrenerObjectToTrener).collect(Collectors.toList());

    }

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

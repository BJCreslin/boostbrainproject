package ru.bjcreslin.service;

import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.jsonobjects.JSONTrenerObject;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для методов преобразования объектов
 */
public class objectConversionService {

    public static Trener jSONTrenerObjectToTrener(JSONTrenerObject jsonTrenerObject) {
        Trener trener = new Trener();
        trener.setName(jsonTrenerObject.getName());
        trener.setLastName(jsonTrenerObject.getLastName());
        trener.setMiddleName(jsonTrenerObject.getMiddleName());
        trener.setGender(jsonTrenerObject.getGender());
        jsonTrenerObject.getAcademicDegree().forEach(x -> trener.getAcademicDegree().add((String) x));
        jsonTrenerObject.getSport().forEach(x -> trener.getSport().add(x));
        jsonTrenerObject.getEducation().forEach(x -> trener.getEducation().add((String)x));
        return trener;
    }

    public static JSONTrenerObject wrapperToTrener(JSONWrapperObject jsonWrapperObject) {
        return jsonWrapperObject.getCells();
    }

    public static List<Trener> listJSONWrapperToTrenerList(List<JSONWrapperObject> wrapperObjectList) {
        List<Trener> trenerList =
                wrapperObjectList.stream().map(x -> wrapperToTrener(x)).map(x -> jSONTrenerObjectToTrener(x)).collect(Collectors.toList());
        return trenerList;
    }

}

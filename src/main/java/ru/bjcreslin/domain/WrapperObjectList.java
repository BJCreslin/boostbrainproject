package ru.bjcreslin.domain;

import lombok.extern.java.Log;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранения данных в памяти
 */
@Log
public class WrapperObjectList {
    public static List<JSONWrapperObject> getObjectList() {
        log.info("Данные взяты из кеша памяти, всего " + getObjectList().size() + " объектов");
        return objectList;
    }

    public static void setObjectList(List<JSONWrapperObject> objectListin) {
          WrapperObjectList.objectList = objectListin;
        log.info("Данные закешированы в память, всего " + size() + " объектов");
    }

    private static List<JSONWrapperObject> objectList = new ArrayList<>();

    public static int size() {
        return objectList.size();
    }
}

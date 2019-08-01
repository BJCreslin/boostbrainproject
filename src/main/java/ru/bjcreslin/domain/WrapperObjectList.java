package ru.bjcreslin.domain;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранения данных в памяти
 */
@Data
@Component
@Log
public class WrapperObjectList {
    public static List<JSONWrapperObject> getObjectList() {
        log.info("Данные взяты из кеша ХДД, всего " + getObjectList().size() + " объектов");
        return objectList;
    }

    public static void setObjectList(List<JSONWrapperObject> objectList) {
        WrapperObjectList.objectList = objectList;
        log.info("Данные закешированы на ХДД, всего " + objectList.size() + " объектов");
    }

    private static List<JSONWrapperObject> objectList = new ArrayList<>();
}

package ru.bjcreslin.service;

import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;

import java.util.List;

public interface WebService {

    /**
     * Выдает количество объектов с сайта
     *
     * @return int- количество
     */
    int getCount();

    /**
     * Получает все данные с сайта и возращает list POJO
     */
    List<JSONWrapperObject> getAll() throws ErrorParsingTxtJsonToPojo;

    /**
     * Получает текстовые данные с Сервера.
     * Проверяет условие:
     * Внимание: при запросе датасетов с количеством записей более 10000шт., в ответе будет передан статус 413.
     * Получение таких датасетов возможно с применением описанных ниже параметров $top (максимальное допустимое значение - 500шт.) и $skip.
     *
     * @return строка с данными
     */
    String getTextJSONData();

    /**
     * преобразует тект в лист POJO
     *
     * @param txt
     * @return list
     */
    List<JSONWrapperObject> textToArrayOfJsonConverter(String txt) throws ErrorParsingTxtJsonToPojo;
}

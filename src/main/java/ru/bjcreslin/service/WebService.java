package ru.bjcreslin.service;

import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;

import java.util.List;

public interface WebService {

    /**
     * Выдает количество объектов с сайта
     *
     * @return int- количество
     */
    int getCount() throws ErrorConectionToMosRuServer;

    /**
     * Получает все данные с сайта и возращает list POJO
     */
    List<JSONWrapperObject> getAll() throws ErrorParsingTxtJsonToPojo, ErrorConectionToMosRuServer;

    /**
     * Получает текстовые данные с Сервера.
     * Проверяет условие:
     * Внимание: при запросе датасетов с количеством записей более 10000шт., в ответе будет передан статус 413.
     * Получение таких датасетов возможно с применением описанных ниже параметров $top (максимальное допустимое значение - 500шт.) и $skip.
     *
     * @return строка с данными
     */
    String getTextJSONData() throws ErrorConectionToMosRuServer;


    /**
     * ерсия api
     *
     * @return api
     */
    APIVersion getVersionApi() throws ErrorConectionToMosRuServer, ErrorApiVersionCheck;

    /**
     * Версия набора данных
     */
    DataSetsVersion getDataSetsVersion() throws ErrorApiVersionCheck, ErrorConectionToMosRuServer;
}

package ru.bjcreslin.domain.apiobjects;

import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;

public abstract class DataObiect {

    /**
     * Возвращает версию API
     *
     * @return String в JSON с текущей версией API
     */
    abstract APIVersion getApiversion() throws ErrorConectionToMosRuServer, ErrorApiVersionCheck;

    /**
     * Функция получения страницы по адрессу
     *
     * @param address -web adress
     * @return Веб страницу в виде текста
     */
    abstract String getPageFromUrl(String address) throws ErrorConectionToMosRuServer;

    /**
     * генерирует адрес запроса
     *
     * @param adressPart- часть запроса между адресом сервера , версиейAPI, datasets  и ключом
     * @return полный адрес запроса в виде строки
     * описание части строки смотри на https://apidata.mos.ru/Docs
     */
    abstract String generatedAdress(String adressPart);

    /**
     * Получение версии набора данных с сервера.
     *
     * @param idDataSets // "Id": 61321,  - ID данных по тренерам
     * @return объект DataSetsVersion
     * @throws ErrorConectionToMosRuServer
     * @throws ErrorApiVersionCheck
     */
    public abstract DataSetsVersion getDataSetsVersion(int idDataSets) throws ErrorConectionToMosRuServer, ErrorApiVersionCheck;
}

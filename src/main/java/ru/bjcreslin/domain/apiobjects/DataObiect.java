package ru.bjcreslin.domain.apiobjects;

import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;

public abstract class DataObiect {

    abstract String getApiversion() throws ErrorConectionToMosRuServer;

    abstract String getPageFromUrl(String address) throws ErrorConectionToMosRuServer;

    abstract String generatedAdress(String adressPart);
}

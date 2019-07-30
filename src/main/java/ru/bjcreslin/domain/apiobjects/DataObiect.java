package ru.bjcreslin.domain.apiobjects;

import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;

public abstract class DataObiect {

    abstract APIVersion getApiversion() throws ErrorConectionToMosRuServer, ErrorApiVersionCheck;

    abstract String getPageFromUrl(String address) throws ErrorConectionToMosRuServer;

    abstract String generatedAdress(String adressPart);

    public abstract DataSetsVersion getDataSetsVersion(int idDataSets) throws ErrorConectionToMosRuServer, ErrorApiVersionCheck;
}

package ru.bjcreslin.domain.apiobjects;

public abstract class DataObiect {

    abstract String getApiversion();

    abstract String getPageFromUrl(String address);

    abstract String generatedAdress(String adressPart);
}

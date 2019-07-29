package ru.bjcreslin.domain.apiobjects;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MosRuDataServerTest {
    private MosRuDataServer mosRuDataServer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mosRuDataServer = new MosRuDataServer();
    }

    @org.junit.jupiter.api.Test
    void getApiversionIsString() {

        Assert.assertTrue(mosRuDataServer.getApiversion().getClass() == String.class);
    }

    @Test
    void getPageFromUrl() {
        String address = "https://apidata.mos.ru/version";
        Assert.assertTrue(mosRuDataServer.getPageFromUrl(address).equalsIgnoreCase("{\"Version\":1}"));
    }

    @Test
    void getApiversion() {
        Assert.assertTrue(mosRuDataServer.getApiversion().equalsIgnoreCase("{\"Version\":1}"));
    }


    @Test
    void getApikey() {
        Assert.assertTrue(mosRuDataServer.getApikey().equalsIgnoreCase("api_key=586d058a1a8ef94f0cd1105d4c0550e9"));
    }

    @Test
    void generatedAdress() {
        String webAdressPart = "333/count";
        Assert.assertTrue(mosRuDataServer.generatedAdress(webAdressPart).equalsIgnoreCase(
                "https://apidata.mos.ru/v1/datasets/333/count?api_key=586d058a1a8ef94f0cd1105d4c0550e9"));
    }
}
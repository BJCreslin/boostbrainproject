package ru.bjcreslin.domain.apiobjects;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;

class MosRuDataServerTest {
    private MosRuDataServer mosRuDataServer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mosRuDataServer = new MosRuDataServer();
    }

    @org.junit.jupiter.api.Test
    void getApiversionIsString() throws ErrorConectionToMosRuServer, ErrorApiVersionCheck {

        Assert.assertTrue(mosRuDataServer.getApiversion().getClass() == APIVersion.class);
    }

    @Test
    void getPageFromUrl() throws ErrorConectionToMosRuServer {
        String address = "https://apidata.mos.ru/version";
        Assert.assertTrue(mosRuDataServer.getPageFromUrl(address).equalsIgnoreCase("{\"Version\":1}"));
    }

    @Test
    void getApiversion() throws ErrorConectionToMosRuServer, ErrorApiVersionCheck {
        Assert.assertTrue(mosRuDataServer.getApiversion().getVersion().equalsIgnoreCase("1"));
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
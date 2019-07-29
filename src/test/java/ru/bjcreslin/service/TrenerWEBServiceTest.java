package ru.bjcreslin.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bjcreslin.domain.apiobjects.MosRuDataServer;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;

import java.util.List;

class TrenerWEBServiceTest {
    private WebService webService;

    @BeforeEach
    void SetUp() {
        webService = new TrenerWEBService(new MosRuDataServer());
    }

    @Test
    void getCount() throws ErrorConectionToMosRuServer {
        int count = webService.getCount();
        System.out.println(count);
        Assert.assertTrue(count > -1);
    }

    @Test
    void getAll() throws ErrorParsingTxtJsonToPojo, ErrorConectionToMosRuServer {
        List<JSONWrapperObject> trenerList = webService.getAll();
        System.out.println(trenerList.size());
    }
}
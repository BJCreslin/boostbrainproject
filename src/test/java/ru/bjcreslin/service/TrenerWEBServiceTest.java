package ru.bjcreslin.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.apiobjects.MosRuDataServer;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;

import java.util.List;

class TrenerWEBServiceTest {
    WebService webService;

    @BeforeEach
    void SetUp() {
        webService = new TrenerWEBService(new MosRuDataServer());
    }

    @Test
    void getCount() {
        int count = webService.getCount();
        System.out.println(count);
        Assert.assertTrue(count > -1);
    }

    @Test
    void getAll() {
        List<JSONWrapperObject> trenerList = webService.getAll();
        System.out.println(trenerList.size());
    }
}
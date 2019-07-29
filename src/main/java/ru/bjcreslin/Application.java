package ru.bjcreslin;

import ru.bjcreslin.domain.apiobjects.MosRuDataServer;

public class Application {
    public static void main(String[] args) {
        MosRuDataServer mosRuDataServer = new MosRuDataServer();
        System.out.println(mosRuDataServer.getApiversion());
    }
}

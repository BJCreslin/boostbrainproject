package ru.bjcreslin.service;

import lombok.extern.java.Log;
import ru.bjcreslin.domain.apiobjects.MosRuDataServer;

@Log
public class TrenerWEBService implements WebService {
    public TrenerWEBService(MosRuDataServer dataServer) {
        this.dataServer = dataServer;
    }

    MosRuDataServer dataServer;


    @Override
    public int getCount() {
        String idDataGroupe = "61321";
        String urlForReceice = dataServer.generatedAdress(idDataGroupe + "/count");
        String count = dataServer.getPageFromUrl(urlForReceice);
        return Integer.parseInt(count);
    }
}

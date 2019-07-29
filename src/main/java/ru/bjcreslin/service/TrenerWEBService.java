package ru.bjcreslin.service;

import lombok.extern.java.Log;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.apiobjects.MosRuDataServer;

import java.util.List;

@Log
public class TrenerWEBService implements WebService {
    private static final String ID_DATA_GROUPE = "61321";

    public TrenerWEBService(MosRuDataServer dataServer) {
        this.dataServer = dataServer;
    }

    MosRuDataServer dataServer;


    @Override
    public int getCount() {

        String urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/count");
        String count = dataServer.getPageFromUrl(urlForReceice);
        return Integer.parseInt(count);
    }

    @Override
    public List<Trener> getAll() {
        String urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/rows" + "?$top=1");
        log.info(urlForReceice);
        String txt = dataServer.getPageFromUrl(urlForReceice);
        System.out.println(txt);
        return null;
    }

}

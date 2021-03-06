package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.apiobjects.MosRuDataServer;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;

import java.util.List;

@Log
@Service
public class TrenerWEBService implements WebService {
    private static final String ID_DATA_GROUPE = "61321"; // "Id": 61321,  - ID данных по тренерам
    private final MosRuDataServer dataServer;
    private static int count = 0;
    private final ObjectConversionService objectConversionService;

    public TrenerWEBService(MosRuDataServer dataServer,ObjectConversionService objectConversionService) {
        this.dataServer = dataServer;
        this.objectConversionService=objectConversionService;
    }

    /**
     * Возвращает колиество данных
     */
    @Override
    public int getCount() throws ErrorConectionToMosRuServer {
        /*Если данные по count уже есть, то берем их */
        if (count == 0) {
            String urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/count");
            count = Integer.parseInt(dataServer.getPageFromUrl(urlForReceice));
        }
        return count;
    }

    /**
     * Возвращает все данные в виде лист
     */
    @Override
    public List<JSONWrapperObject> getAll() throws ErrorParsingTxtJsonToPojo, ErrorConectionToMosRuServer {
        String txt = getTextJSONData();
        return objectConversionService.textToArrayOfJsonConverter(txt);
    }

    /**
     * Возвращает версию АПИ данных Мосдата
     */
    @Override
    public APIVersion getVersionApi() throws ErrorConectionToMosRuServer, ErrorApiVersionCheck {
        return dataServer.getApiversion();
    }

    /**
     * Возвращает версию данных набора Мосдата
     */
    @Override
    public DataSetsVersion getDataSetsVersion() throws ErrorApiVersionCheck, ErrorConectionToMosRuServer {
        return dataServer.getDataSetsVersion(Integer.parseInt(ID_DATA_GROUPE));
    }

    /**
     * Получает данные с сервера МОСДАТА и возвращает их в виде String
     */
    @Override
    public String getTextJSONData() throws ErrorConectionToMosRuServer {
        int countAll = getCount();
        StringBuilder txt = new StringBuilder();
        String urlForReceice = "";
        /*
        Внимание: при запросе датасетов с количеством записей более 10000шт., в ответе будет передан статус 413.
        Получение таких датасетов возможно с применением описанных ниже параметров $top (максимальное допустимое значение - 500шт.) и $skip.
         */
        if (countAll > 9999) {
            for (int i = 1; i <= countAll; i += 500) {
                urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/rows" + "?$top=" + 500 + "&$skip=" + i);
                txt.append(dataServer.getPageFromUrl(urlForReceice));
            }
        } else {
            urlForReceice = dataServer.generatedAdress(ID_DATA_GROUPE + "/rows");
            txt.append(dataServer.getPageFromUrl(urlForReceice));
        }
        log.info("url:" + urlForReceice + " получены:" + txt.length() + " символов");
        return txt.toString();
    }

}


/*
 "Id": 61321,
        "VersionNumber": 1,
        "ReleaseNumber": 7,
        "Caption": "Открытый реестр тренеров и специалистов (в том числе педагогов дополнительного образования по дополнительным общеразвивающим программам) в области физической культуры и спорта города Москвы",
        "CategoryId": 15,
        "DepartmentId": 16,
        "PublishDate": "25.01.2019",
        "FullDescription": "Открытый реестр тренеров и иных специалистов в области физической культуры и спорта, деятельность которых связана со спортивной подготовкой и проведением занятий по физической культуре и спорту (в том числе педагогах дополнительного образования по дополнительным общеразвивающим программам, инструкторах по спорту, инструкторах-методистах физкультурно-спортивных организаций и др.), осуществляющих свою деятельность на территории города Москвы&raquo;.<br />\nКаталог содержит информацию о тренерах имеющих:<br />\n- соответствующее среднее профессиональное образование или высшее образование в области физической культуры и спорта;<br />\n- соответствующее дополнительное профессиональное образование в области физической культуры и спорта;<br />\n- личные высокие спортивные достижения;<br />\n- соответствующие документы о повышении квалификации в области физической культуры и спорта, выданные аккредитованными спортивными федерациями.",
        "Keywords": "реестр тренеров, москва, спорт, физическая культура, дополнительные общеразвивающие программы, педагоги дополнительногообразования, реестр специалистов",
        "ContainsGeodata": false,
        "ContainsAccEnvData": false,
        "IsForeign": false,
        "IsSeasonal": false,
        "Season": "0",
        "IsArchive": false,
        "IsNew": false,
        "LastUpdateDate": "25.01.2019",
        "SefUrl": "7708308010-otkrytiy-reestr-trenerov-i-spetsialistov",
        "IdentificationNumber": "7708308010-Otkrytyyreestrtrenerovispetsialistov"
    },
 */
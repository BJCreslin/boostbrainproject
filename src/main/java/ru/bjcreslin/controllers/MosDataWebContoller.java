package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.WrapperObjectList;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;
import ru.bjcreslin.repository.JSONTrenerRepository;
import ru.bjcreslin.service.TrenerDBService;
import ru.bjcreslin.service.TrenerWEBService;

import java.util.List;

@RequestMapping("mosdata")
@Controller
@Log
public class MosDataWebContoller {
    private static final int maxElementsOnScreen = 20;
    private TrenerWEBService trenerWEBService;
    private TrenerDBService trenerDBService;

    public MosDataWebContoller(TrenerWEBService trenerWEBService, TrenerDBService trenerDBService, JSONTrenerRepository jsonTrenerRepository) {
        this.trenerWEBService = trenerWEBService;
        this.trenerDBService = trenerDBService;
        this.pageable = PageRequest.of(0, maxElementsOnScreen);
    }

    private Pageable pageable;

    @GetMapping("/versionAPI")
    public String getApiVersion(Model model) {
        try {
            APIVersion version = trenerWEBService.getVersionApi();
            String txtOUT = "Текущая версия API:" + version.getVersion();
            model.addAttribute("textAPI", txtOUT);
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "шибка соединения с сервером МОСДАТА");
            return "errorpage";
        } catch (ErrorApiVersionCheck errorApiVersionCheck) {
            model.addAttribute("errorText", "шибка получения версии API");
            return "errorpage";
        }
        return "versionapi";
    }

    @GetMapping("/versionDatasets")
    public String getVersionDatasets(Model model) {
        try {
            DataSetsVersion version = trenerWEBService.getDataSetsVersion();
            String txtOUT = "Текущая версия набора данных:" + version.getVersionNumber() +
                    ". Текущий релиз: " + version.getReleaseNumber();
            model.addAttribute("textAPI", txtOUT);
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "Ошибка соединения с сервером МОСДАТА");
            return "errorpage";
        } catch (ErrorApiVersionCheck errorApiVersionCheck) {
            model.addAttribute("errorText", "Ошибка получения версии.");
            return "errorpage";
        }
        return "versionapi";

    }

    @GetMapping("/number")
    public String getNumber(Model model) {
        try {
            String txtOUT = "Количество данных в наборе данных: " + trenerWEBService.getCount();
            model.addAttribute("textAPI", txtOUT);
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "Ошибка соединения с сервером МОСДАТА");
            return "errorpage";
        }
        return "versionapi";
    }


    @GetMapping("loaddata")
    public String getData(Model model) {
        try {
            //кешируем полученные с сервера Мосдата данные в память
            WrapperObjectList.setObjectList(trenerWEBService.getAll());
            log.info("данные сервера закешированы в память. всего " + WrapperObjectList.getObjectList().size());
            // Преобразуем и сохраняем даныне для анализа
            List<JSONWrapperObject> jsonWrapperObjectList=WrapperObjectList.getObjectList();
            log.info("  List<JSONWrapperObject> jsonWrapperObjectList=WrapperObjectList.getObjectList();");
            trenerDBService.saveJSONWrapperObjectListToTrenersDBase(jsonWrapperObjectList);

        } catch (ErrorParsingTxtJsonToPojo errorParsingTxtJsonToPojo) {
            model.addAttribute("errorText", "Ошибка получения данных с сервера МОСДАТА");
            return "errorpage";
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "Ошибка соединения с сервером МОСДАТА");
            return "errorpage";
        }
        model.addAttribute("item_name", "mosdatav");
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        return "showAll";
    }

    @GetMapping("/first")
    public String showFirst(Model model) {
        pageable = pageable.first();
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "mosdata");
        return "showAll";
    }

    @GetMapping("/prev")
    public String showPrev(Model model) {
        pageable = pageable.previousOrFirst();
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "mosdata");
        return "showAll";
    }

    @GetMapping("/next")
    public String showNext(Model model) {
        pageable = pageable.next();
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "mosdata");
        return "showAll";
    }
}

package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;
import ru.bjcreslin.repository.JSONTrenerRepository;
import ru.bjcreslin.service.FileService;
import ru.bjcreslin.service.ObjectConversionService;
import ru.bjcreslin.service.TrenerDBService;
import ru.bjcreslin.service.TrenerWEBService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("mosdata")
@Controller
@Log
public class MosDataWebContoller {
    private static final int maxElementsOnScreen = 20;
    private TrenerWEBService trenerWEBService;
    private TrenerDBService trenerDBService;
    private FileService fileService;
    private ObjectConversionService objectConversionService;
    private JSONTrenerRepository jsonTrenerRepository;

    public MosDataWebContoller(TrenerWEBService trenerWEBService, TrenerDBService trenerDBService, JSONTrenerRepository jsonTrenerRepository,
                               FileService fileService, ObjectConversionService objectConversionService) {
        this.trenerWEBService = trenerWEBService;
        this.trenerDBService = trenerDBService;
        this.jsonTrenerRepository = jsonTrenerRepository;
        this.pageable = PageRequest.of(0, maxElementsOnScreen);
        this.trenerWEBServiceAll = new ArrayList<>();
        this.fileService = fileService;
        this.objectConversionService = objectConversionService;
    }


    private Pageable pageable;
    List<JSONWrapperObject> trenerWEBServiceAll;

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
            trenerWEBServiceAll = trenerWEBService.getAll();
            trenerDBService.saveTrenersToBase(trenerWEBServiceAll);

        } catch (ErrorParsingTxtJsonToPojo errorParsingTxtJsonToPojo) {
            model.addAttribute("errorText", "Ошибка получения данных с сервера МОСДАТА");
            return "errorpage";
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "Ошибка соединения с сервером МОСДАТА");
            return "errorpage";
        }
        model.addAttribute("item_name", "mosdata");
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        return "showAll";
    }

    @GetMapping("savedatatofile")
    public String savetofile(Model model) {
        fileService.saveBaseFile(objectConversionService.listJSonWrapperToTxt(trenerWEBServiceAll));
        return "showAll";
    }
}

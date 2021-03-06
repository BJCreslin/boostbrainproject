package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.ItemsMenuActivatorVariable;
import ru.bjcreslin.domain.WrapperObjectList;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;
import ru.bjcreslin.service.TrenerDBService;
import ru.bjcreslin.service.TrenerWEBService;

import java.util.List;

@RequestMapping("mosdata")
@Controller
@Log
/*
  ВебКонтроллер для управления получением данных с сервера МОСДАТА
 */
public class MosDataWebContoller {
    private static final int maxElementsOnScreen = 20;
    private final TrenerWEBService trenerWEBService;
    private final TrenerDBService trenerDBService;

    public MosDataWebContoller(TrenerWEBService trenerWEBService, TrenerDBService trenerDBService) {
        this.trenerWEBService = trenerWEBService;
        this.trenerDBService = trenerDBService;
        this.pageable = PageRequest.of(0, maxElementsOnScreen);
    }

    private Pageable pageable;

    /**
     * Получает с срвера МОсдаты версию АПИ
     */
    @GetMapping("/versionAPI")
    public String getApiVersion(Model model) {
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
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

    /**
     * Получает с МОСДАТы версию набора данных
     */
    @GetMapping("/versionDatasets")
    public String getVersionDatasets(Model model) {
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
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

    /**
     * Количество элементов в наборе данных
     */
    @GetMapping("/number")
    public String getNumber(Model model) {
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        try {
            String txtOUT = "Количество данных в наборе данных: " + trenerWEBService.getCount();
            model.addAttribute("textAPI", txtOUT);
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "Ошибка соединения с сервером МОСДАТА");
            return "errorpage";
        }
        return "versionapi";
    }

    /**
     * Загрузка данных с сервера Мосдата в кеш памяти и БД
     */
    @GetMapping("loaddata")
    public String getData(Model model) {
        try {
            List<JSONWrapperObject> jsonWrapperObjectList = trenerWEBService.getAll();
            //кешируем полученные с сервера Мосдата данные в память
            WrapperObjectList.setObjectList(jsonWrapperObjectList);
            log.info("данные сервера закешированы в память. всего " + WrapperObjectList.size());
            //Сохраняем тренеров в БД
            trenerDBService.saveJSONWrapperObjectListToTrenersDBase(jsonWrapperObjectList);
        } catch (ErrorParsingTxtJsonToPojo errorParsingTxtJsonToPojo) {
            model.addAttribute("errorText", "Ошибка получения данных с сервера МОСДАТА");
            return "errorpage";
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "Ошибка соединения с сервером МОСДАТА");
            return "errorpage";
        }
        ItemsMenuActivatorVariable.setCanSaveDisk(true);
        ItemsMenuActivatorVariable.setCanAnalyse(true);
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("item_name", "mosdata");
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        return "showAll";
    }

    /**
     * Навигационные кнопки
     *
     * @param model -модель
     * @return страницу с другими данными
     */
    @GetMapping("/first")
    public String showFirst(Model model) {
        pageable = pageable.first();
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "mosdata");
        return "showAll";
    }

    /**
     * Навигационные кнопки
     *
     * @param model -модель
     * @return страницу с другими данными
     */
    @GetMapping("/prev")
    public String showPrev(Model model) {
        pageable = pageable.previousOrFirst();
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "mosdata");
        return "showAll";
    }

    /**
     * Навигационные кнопки
     *
     * @param model -модель
     * @return страницу с другими данными
     */
    @GetMapping("/next")
    public String showNext(Model model) {
        pageable = pageable.next();
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "mosdata");
        return "showAll";
    }
}

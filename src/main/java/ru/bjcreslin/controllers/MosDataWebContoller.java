package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
import ru.bjcreslin.domain.jsonobjects.DataSetsVersion;
import ru.bjcreslin.exceptions.ErrorApiVersionCheck;
import ru.bjcreslin.exceptions.ErrorConectionToMosRuServer;
import ru.bjcreslin.service.TrenerWEBService;

@RequestMapping("mosdata")
@Controller
@Log
public class MosDataWebContoller {
    private TrenerWEBService trenerWEBService;

    public MosDataWebContoller(TrenerWEBService trenerWEBService) {
        this.trenerWEBService = trenerWEBService;
    }

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
            String txtOUT = "Текущая версия абора данных:" + version.getVersionNumber() +
                    ". Текущий релиз: " + version.getReleaseNumber();
            model.addAttribute("textAPI", txtOUT);
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "шибка соединения с сервером МОСДАТА");
            return "errorpage";
        } catch (ErrorApiVersionCheck errorApiVersionCheck) {
            model.addAttribute("errorText", "шибка получения версии.");
            return "errorpage";
        }

        return "versionapi";

    }
}

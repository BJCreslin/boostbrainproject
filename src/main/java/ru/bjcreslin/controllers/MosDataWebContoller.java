package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.jsonobjects.APIVersion;
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
        APIVersion version=null;
        try {
            version = trenerWEBService.getVersionApi();
        } catch (ErrorConectionToMosRuServer errorConectionToMosRuServer) {
            model.addAttribute("errorText", "шибка соединения с сервером МОСДАТА");
            return "errorpage";
        } catch (ErrorApiVersionCheck errorApiVersionCheck) {
            model.addAttribute("errorText", "шибка получения версии API");
            return "errorpage";
        }
        model.addAttribute("textAPI", version.getVersion());
        return "versionapi";
    }
}

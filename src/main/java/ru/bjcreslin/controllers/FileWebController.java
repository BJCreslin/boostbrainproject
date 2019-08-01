package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.WrapperObjectList;
import ru.bjcreslin.domain.jsonobjects.JSONWrapperObject;
import ru.bjcreslin.exceptions.ErrorParsingTxtJsonToPojo;
import ru.bjcreslin.service.FileService;
import ru.bjcreslin.service.ObjectConversionService;
import ru.bjcreslin.service.TrenerDBService;

import java.util.List;

import static ru.bjcreslin.domain.WrapperObjectList.setObjectList;

@Controller
@Log
@RequestMapping("file")
public class FileWebController {
    private static final int maxElementsOnScreen = 20;
    private FileService fileService;
    private ObjectConversionService objectConversionService;
    private Pageable pageable;
    private TrenerDBService trenerDBService;


    public FileWebController(FileService fileService, ObjectConversionService objectConversionService, TrenerDBService trenerDBService) {
        this.fileService = fileService;
        this.objectConversionService = objectConversionService;
        this.trenerDBService = trenerDBService;
        this.pageable = PageRequest.of(0, maxElementsOnScreen);
    }

    @GetMapping("/savedata")
    public String save(Model model) {
        fileService.saveBaseFile(objectConversionService.wrapperObjectListToString(WrapperObjectList.getObjectList()));
        model.addAttribute("item_name", "mosdata");
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        return "showAll";
    }

    @GetMapping("/loaddata")
    public String load(Model model) {
        try {
            String txt1 = fileService.readBaseFile();
            log.info("данные получены из файла. Вего " + txt1.length() + " знаков");
            List<JSONWrapperObject> wrapperObjects = objectConversionService.textToArrayOfJsonConverter(txt1);
            log.info("данные переведены из JSON " + wrapperObjects.size() + " элементов");
            setObjectList(wrapperObjects);
           // log.info("sizr " + WrapperObjectList.getObjectList().size());
        } catch (ErrorParsingTxtJsonToPojo errorParsingTxtJsonToPojo) {
            model.addAttribute("errorText", "Не удалось получить данные из кеша");
            return "errorpage";
        }
        log.info("количество данных в кеше памяти " + WrapperObjectList.size());
        trenerDBService.saveTrenersToBase(WrapperObjectList.getObjectList());
        model.addAttribute("item_name", "mosdata");
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        return "showAll";
    }
}

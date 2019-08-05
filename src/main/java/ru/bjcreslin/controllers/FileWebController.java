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
    private final FileService fileService;
    private final ObjectConversionService objectConversionService;
    private Pageable pageable;
    private final TrenerDBService trenerDBService;


    public FileWebController(FileService fileService, ObjectConversionService objectConversionService, TrenerDBService trenerDBService) {
        this.fileService = fileService;
        this.objectConversionService = objectConversionService;
        this.trenerDBService = trenerDBService;
        this.pageable = PageRequest.of(0, maxElementsOnScreen);
    }

    @GetMapping("/savedata")
    public String save(Model model) {
        fileService.saveBaseFile(objectConversionService.wrapperObjectListToString(WrapperObjectList.getObjectList()));
        ItemsMenuActivatorVariable.setCanLoadDisk(true);
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("item_name", "file");
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
            log.info("количество данных в кеше памяти " + WrapperObjectList.size());
            log.info("Очистка данных в дб");
            trenerDBService.clearBase();
            trenerDBService.saveJSONWrapperObjectListToTrenersDBase(wrapperObjects);

        } catch (ErrorParsingTxtJsonToPojo errorParsingTxtJsonToPojo) {
            model.addAttribute("errorText", "Не удалось получить данные из кеша");
            return "errorpage";
        }
        ItemsMenuActivatorVariable.setCanSaveDisk(true);
        ItemsMenuActivatorVariable.setCanAnalyse(true);
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("item_name", "file");
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        return "showAll";
    }

    /**
     * Навигационные кнопки
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
     * @param model -модель
     * @return страницу с другими данными
     */
    @GetMapping("/prev")
    public String showPrev(Model model) {
        pageable = pageable.previousOrFirst();
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "file");
        return "showAll";
    }

    /**
     * Навигационные кнопки
     * @param model -модель
     * @return страницу с другими данными
     */
    @GetMapping("/next")
    public String showNext(Model model) {
        pageable = pageable.next();
        ItemsMenuActivatorVariable.itemMenuEnabled(model);
        model.addAttribute("itemsSP", trenerDBService.findAll(pageable));
        model.addAttribute("item_name", "file");
        return "showAll";
    }


}

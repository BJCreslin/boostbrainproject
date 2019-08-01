package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.WrapperObjectList;
import ru.bjcreslin.service.FileService;
import ru.bjcreslin.service.ObjectConversionService;

@Controller
@Log
@RequestMapping("file")
public class FileWebController {
    private FileService fileService;
    private ObjectConversionService objectConversionService;

    public FileWebController(FileService fileService, ObjectConversionService objectConversionService) {
        this.fileService = fileService;
        this.objectConversionService = objectConversionService;
    }

    @GetMapping("/loaddata")
    public void load(Model model) {
        fileService.saveBaseFile(objectConversionService.wrapperObjectListToString(WrapperObjectList.getObjectList()));

    }
}

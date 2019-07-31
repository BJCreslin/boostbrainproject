package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.service.FileService;

@Controller
@Log
@RequestMapping("file")
public class FileWebController {
    @Autowired
    FileService fileService;

    @GetMapping("/load")
    public void load(Model model) {


    }
}

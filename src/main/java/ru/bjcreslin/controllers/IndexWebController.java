package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.ItemsMenuActivatorVariable;

import java.nio.file.Files;
import java.nio.file.Paths;


@Controller
@Log
@RequestMapping("")
public class IndexWebController {

    /**
     * Контроллер главной старницы (index)
     */
    @GetMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        log.info("get index");


        if (Files.exists(Paths.get("rows.zip"))) {
            ItemsMenuActivatorVariable.setCanLoadDisk(true);
        }
        ItemsMenuActivatorVariable.itemMenuEnabled(model);

        return "index";
    }


}

package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@Log
@RequestMapping("")
public class IndexWebController {

    @GetMapping({"", "/", "/index"})
    public String getIndex(HttpSession session) {
        log.info("get index");
        return "index";
    }
}

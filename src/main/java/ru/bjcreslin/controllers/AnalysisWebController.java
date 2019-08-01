package ru.bjcreslin.controllers;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.domain.Trener;
import ru.bjcreslin.domain.jsonobjects.Sport;
import ru.bjcreslin.service.TrenerDBService;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
@Log
@RequestMapping("analysis")
public class AnalysisWebController {
    private TrenerDBService trenerDBService;

    @GetMapping("/sport")
    public String getSportbyTrenersNumber(Model model) {
        Map<String, Integer> sportsCount = new HashMap<>();
        for (Trener trener : trenerDBService.findAll()) {
            for (Sport sport : trener.getSport()) {
                if (sportsCount.containsKey(sport.getSportName())) {
                    sportsCount.replace(sport.getSportName(), sportsCount.get(sport.getSportName()));
                } else {
                    sportsCount.put(sport.getSportName(), 1);
                }
            }
        }

        // Сортируем
        Map<Integer, String> sportsCountSort = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : sportsCount.entrySet()) {
            sportsCountSort.put(entry.getValue(), entry.getKey());
        }
        model.addAttribute("mapwithdata", sportsCountSort);
        return "analyse";
    }
}

package com.example.demo.controllers;

import com.example.demo.config.HistoryEnter;
import com.example.demo.models.History;
import com.example.demo.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/history")
public class HistoryController {
    private final HistoryService historyService;

    @Autowired
    HistoryController(HistoryService historyService) {this.historyService = historyService;}

    @GetMapping(path = "/all")
    public String getAll(Model model) {
        model.addAttribute("histories", historyService.getAll());
        return "allHistories";
    }

    @GetMapping(path = "/{id}")
    public History getByID(@PathVariable("id") String id) { return historyService.getBy(id); }

    @GetMapping("/add")
    public String historyForm(Model model) {
        model.addAttribute("history", new HistoryEnter());
        return "addHistory";
    }
    @PostMapping("/add")
    public String historySubmit(@ModelAttribute HistoryEnter historyEnter, Model model) {
        model.addAttribute("history", historyEnter);
        historyService.addNewHistory(historyEnter);
        return "postHistory";
    }

    @GetMapping("/getYear")
    @ResponseBody
    public List<History> getLogsByYear(@RequestParam int year) { return historyService.getLogByYear(year); }

    @GetMapping("/getYearAgg")
    @ResponseBody
    public String getAllByYear(@RequestParam int year) { return historyService.getAllByYear(year); }

    @GetMapping("/getMonth")
    @ResponseBody
    public History getByMonth(@RequestParam int year, @RequestParam int month) { return historyService.getByMonth(year, month); }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteHistory(@PathVariable("id") String id) { historyService.deleteById(id); }

    @PutMapping(path = "/update/{id}")
    public void updateHistory(@RequestBody HistoryEnter historyEnter, @PathVariable int id) { historyService.updateHistory(historyEnter, id); }
}

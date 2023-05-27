package com.example.demo.controllers;

import com.example.demo.config.ClientEnter;
import com.example.demo.config.HistoryEnter;
import com.example.demo.models.History;
import com.example.demo.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/history")
public class HistoryController {
    private final HistoryService historyService;

    @Autowired
    HistoryController(HistoryService historyService) {this.historyService = historyService;}

    @GetMapping(path = "/all")
    public List<History> getAll() { return historyService.getAll(); }

    @GetMapping(path = "/{id}")
    public History getByID(@PathVariable("id") String id) {
        return historyService.getBy(id);
    }

    @PostMapping(path = "/add")
    public void addHistory(@RequestBody HistoryEnter historyEnter) { historyService.addNewHistory(historyEnter); }

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

    @PutMapping(path = "update/{id}")
    public void updateHistory(@RequestBody HistoryEnter historyEnter, @PathVariable int id) { historyService.updateHistory(historyEnter, id); }
}

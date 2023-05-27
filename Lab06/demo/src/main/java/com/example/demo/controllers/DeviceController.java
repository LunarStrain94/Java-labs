package com.example.demo.controllers;

import com.example.demo.config.DeviceEnter;
import com.example.demo.models.Device;
import com.example.demo.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/api/device")
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    DeviceController(DeviceService deviceService) {this.deviceService = deviceService;}

    @GetMapping(path = "/all/{page}")
    public String getAll(Model model, @PathVariable("page") int page) {
        model.addAttribute("devices", deviceService.getAll().subList((page-1)*5, deviceService.getAll().size() < (page-1)*5+5 ? deviceService.getAll().size() : (page-1)*5+5));
        int num = deviceService.getAll().size() / 5 + 1;
        List<Integer> li = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            li.add(i+1);
        }
        model.addAttribute("list", li);
        return "allDevices";
    }

    @GetMapping(path = "/{id}")
    public String getById(Model model, @PathVariable("id") String id) {
        model.addAttribute("device", deviceService.getBy(id));
        model.addAttribute("history", deviceService.getBy(id).getHistory());
        return "singleDevice";
    }

    @GetMapping("/add")
    public String deviceForm(Model model) {
        model.addAttribute("device", new DeviceEnter());
        return "addDevice";
    }
    @PostMapping("/add")
    public String deviceSubmit(@ModelAttribute DeviceEnter deviceEnter, Model model) {
        model.addAttribute("device", deviceEnter);
        deviceService.addNewDevice(deviceEnter);
        return "postDevice";
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteDevice(@PathVariable("id") String id) { deviceService.deleteById(id); }

    @GetMapping(path = "/allD")
    @ResponseBody
    public List<Device> getAll() {
        return deviceService.getAll();
    }

    @GetMapping("/devices")
    public String getAllPages(Model model) { RestTemplate restTemplate = new RestTemplate();
        Device device = restTemplate.getForObject("http://localhost:8080/api/device/1", Device.class);
        model.addAttribute("device", device);
        return "device";
    }
}

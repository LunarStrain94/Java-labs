package com.example.demo.controllers;

import com.example.demo.config.DeviceEnter;
import com.example.demo.models.Device;
import com.example.demo.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/device")
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    DeviceController(DeviceService deviceService) {this.deviceService = deviceService;}

    @GetMapping(path = "/all")
    public List<Device> getAll() {
        return deviceService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Device getByID(@PathVariable("id") String id) { return deviceService.getBy(id); }

    @PostMapping(path = "/add")
    public void addDevice(@RequestBody DeviceEnter device) { deviceService.addNewDevice(device); }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteDevice(@PathVariable("id") String id) { deviceService.deleteById(id); }
}

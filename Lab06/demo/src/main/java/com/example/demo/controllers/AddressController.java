package com.example.demo.controllers;

import com.example.demo.config.AddressEnter;
import com.example.demo.models.Address;
import com.example.demo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(path = "/all")
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Address getByID(@PathVariable("id") String id) {
        return addressService.getBy(id);
    }

    @PostMapping(path = "/add")
    public void addAddress(@RequestBody AddressEnter addressEnter) { addressService.addNewAddress(addressEnter); }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAddress(@PathVariable("id") String id) { addressService.deleteById(id); }
}

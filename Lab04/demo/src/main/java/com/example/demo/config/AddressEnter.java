package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressEnter {
    private String city;
    private String street;
    private String zipCode;
    private int clientId;

    AddressEnter() {}
}

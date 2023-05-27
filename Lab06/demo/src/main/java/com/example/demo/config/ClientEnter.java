package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ClientEnter {
    private String name;
    private String street;
    private String city;
    private String zipCode;
    private String deviceName;
}

package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeviceEnter {
    private int clientId;
    private String name;

    public DeviceEnter() {}
}

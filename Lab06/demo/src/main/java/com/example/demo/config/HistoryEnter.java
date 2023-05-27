package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class HistoryEnter {
    private int deviceId;
    private int month;
    private int year;
    private int fullCons;
    private int avgCons;

    public HistoryEnter() {}
}

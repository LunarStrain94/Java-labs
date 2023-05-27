package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String deviceName;
    @OneToOne(mappedBy = "device")
    @JsonBackReference
    private Client client;
    @OneToMany(mappedBy = "device", orphanRemoval = true, cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<History> history = new ArrayList<>();

    public Device() {}

    public Device(String deviceName) {
        this.deviceName = deviceName;
    }
}

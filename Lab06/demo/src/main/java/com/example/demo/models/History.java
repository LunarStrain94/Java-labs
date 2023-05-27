package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Entity
@Table(name = "historylog")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int averageConsumption;
    private int fullConsumption;
    private int monthT;
    private int yearT;
    @ManyToOne
    @JoinColumn(name="device_id")
    @JsonBackReference
    private Device device;


    public History() {
    }
}

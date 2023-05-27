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
        this.fullConsumption = ThreadLocalRandom.current().nextInt(0, 1000001);
        this.averageConsumption = ThreadLocalRandom.current().nextInt(0, this.fullConsumption);
    }

    public History(int month, int year) {
        this.fullConsumption = ThreadLocalRandom.current().nextInt(0, 1000001);
        this.averageConsumption = ThreadLocalRandom.current().nextInt(0, this.fullConsumption);
        this.monthT = month;
        this.yearT = year;
    }
}

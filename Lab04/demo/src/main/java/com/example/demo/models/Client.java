package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", unique=true)
    @JsonManagedReference
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", referencedColumnName = "id", unique=true)
    @JsonManagedReference
    private Device device;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public void serialize(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filepath), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void serialize(String filepath, List<Client> clients) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filepath), clients);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Client deserialize(String pathname) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Client m = mapper.readValue(new File(pathname), Client.class);
            return m;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Client[] deserializeArray(String pathname) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Client[] m = mapper.readValue(new File(pathname), Client[].class);
            return m;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

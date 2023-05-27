package com.example.demo.repositories;

import com.example.demo.models.Client;
import com.example.demo.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device findById(int id);
}
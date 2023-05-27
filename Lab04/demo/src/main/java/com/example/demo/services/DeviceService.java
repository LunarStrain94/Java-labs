package com.example.demo.services;

import com.example.demo.config.DeviceEnter;
import com.example.demo.models.Client;
import com.example.demo.models.Device;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.Objects.isNull;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final ClientRepository clientRepository;

    @Autowired
    DeviceService(DeviceRepository deviceRepository, ClientRepository clientRepository) {
        this.deviceRepository = deviceRepository;
        this.clientRepository = clientRepository;
    }

    public List<Device> getAll() {return deviceRepository.findAll();}

    public Device getBy(String id) {
        int id1 = Integer.valueOf(id);
        return deviceRepository.findById(id1);
    }

    public void addNewDevice(DeviceEnter deviceEnter) {
        if (!clientRepository.existsById(deviceEnter.getClientId())) {
            throw new IllegalStateException("Client doesn't exist");
        }
        Client client = clientRepository.findById(deviceEnter.getClientId());
        if (!isNull(client.getDevice())) {
            throw new IllegalStateException("Client taken");
        }

        var device = new Device();
        device.setClient(client);
        client.setDevice(device);
        device.setDeviceName(deviceEnter.getName());
        deviceRepository.save(device);
        clientRepository.save(client);
    }

    public void deleteById(String id) {
        int id1 = Integer.valueOf(id);
        boolean deviceExists = deviceRepository.existsById(id1);
        if (!deviceExists) {
            throw new IllegalStateException("Device doesn't exist");
        }
        var client = deviceRepository.getById(id1).getClient();
        client.setDevice(null);
        deviceRepository.deleteById(id1);
    }
}

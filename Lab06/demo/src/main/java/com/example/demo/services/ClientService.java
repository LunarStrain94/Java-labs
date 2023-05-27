package com.example.demo.services;

import com.example.demo.config.ClientEnter;
import com.example.demo.models.Address;
import com.example.demo.models.Client;
import com.example.demo.models.Device;
import com.example.demo.models.History;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.DeviceRepository;
import com.example.demo.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import static java.util.Objects.isNull;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final DeviceRepository deviceRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, DeviceRepository deviceRepository, HistoryRepository historyRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.deviceRepository = deviceRepository;
        this.historyRepository = historyRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(String id) {
        int id1 = Integer.valueOf(id);
        return clientRepository.findById(id1);
    }

    public void addNewClient(ClientEnter clientEnter) {
        List<Client> clients = clientRepository.findAll();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getAddress().getCity() == clientEnter.getCity() && clients.get(i).getAddress().getStreet() == clientEnter.getStreet()
                    && clients.get(i).getAddress().getZipCode() == clientEnter.getZipCode()) {
                throw new IllegalStateException("Month taken");
            }
        }

        var client = new Client();
        client.setName(clientEnter.getName());

        var address = new Address();
        address.setCity(clientEnter.getCity());
        address.setStreet(clientEnter.getStreet());
        address.setZipCode(clientEnter.getZipCode());
        if (!isNull(clientEnter.getCity()) && !isNull(clientEnter.getStreet()) && !isNull(clientEnter.getZipCode())) {
            addressRepository.save(address);
            client.setAddress(address);
        }

        if (!isNull(clientEnter.getDeviceName())) {
            var smartDevice = new Device();
            var history = new History();
            LocalDate currentDate = LocalDate.now();
            int month = currentDate.getMonthValue();
            int year = currentDate.getYear();
            history.setMonthT(month);
            history.setYearT(year);
            historyRepository.save(history);
            history.setDevice(smartDevice);
            smartDevice.setHistory(List.of(history));
            smartDevice.setDeviceName(clientEnter.getDeviceName());
            deviceRepository.save(smartDevice);
            historyRepository.save(history);
            client.setDevice(smartDevice);
        }
        clientRepository.save(client);
    }

    public void deleteById(String id) {
        int id1 = Integer.valueOf(id);
        boolean clientExists = clientRepository.existsById(id1);
        if (!clientExists) {
            throw new IllegalStateException("Client doesn't exist");
        }
        clientRepository.deleteById(id1);
    }
}

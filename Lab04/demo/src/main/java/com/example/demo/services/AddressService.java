package com.example.demo.services;

import com.example.demo.config.AddressEnter;
import com.example.demo.models.Address;
import com.example.demo.models.Client;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;

    @Autowired
    AddressService(AddressRepository addressRepository, ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getBy(String id) {
        int id1 = Integer.valueOf(id);
        return addressRepository.findById(id1);
    }

    public void addNewAddress(AddressEnter addressEnter) {
        Address address = new Address();
        address.setCity(addressEnter.getCity());
        address.setStreet(addressEnter.getStreet());
        address.setZipCode(addressEnter.getZipCode());

        boolean clientTaken = false;
        List<Address> addresses = addressRepository.findAll();
        for (int i = 0; i < addresses.size(); i++) {
            if (addresses.get(i).getClient().getId() == addressEnter.getClientId()) {
                clientTaken = true;
            }
        }

        if (!clientTaken && !isNull(clientRepository.findById(addressEnter.getClientId()))) {
            Client client = clientRepository.findById(addressEnter.getClientId());
            address.setClient(client);
            client.setAddress(address);
            addressRepository.save(address);
        }
    }

    public void deleteById(String id) {
        int id1 = Integer.valueOf(id);
        boolean addressExists = addressRepository.existsById(id1);
        if (!addressExists) {
            throw new IllegalStateException("Address doesn't exist");
        }
        var client = addressRepository.getById(id1).getClient();
        client.setAddress(null);
        addressRepository.deleteById(id1);
    }
}

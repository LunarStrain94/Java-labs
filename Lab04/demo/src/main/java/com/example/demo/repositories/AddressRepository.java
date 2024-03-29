package com.example.demo.repositories;

import com.example.demo.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findById(int id);
    boolean existsByStreet(String street);
    boolean existsByCity(String city);
}
package com.example.demo.config;

import com.example.demo.models.Address;
import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner clr(ClientRepository client) {
        return args -> {
            //Client ante = new Client("Ante Antic");
            //Client franjo = new Client("Franjo Franjic");
            //Address a1 = new Address("Vukovarska 69", "Split", "21000");
            //Address a2 = new Address("Velebitska 42", "Split", "21000");
            //ante.setAddress(a1);
            //franjo.setAddress(a2);
            //a1.setClient(ante);
            //a2.setClient(franjo);
            //List<Client> clients = List.of(ante, franjo);
            //Client.serialize("target/config.json", clients);
            //client.saveAll(clients);

            Client[] cl = Client.deserializeArray("target/config.json");
            List<Client> clients = Arrays.asList(cl);
            client.saveAll(clients);
        };
    }
}

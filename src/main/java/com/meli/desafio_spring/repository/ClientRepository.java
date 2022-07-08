package com.meli.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_spring.exception.NotFoundException;
import com.meli.desafio_spring.model.Client;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClientRepository {

    private static final String DATA = "src/main/resources/client.json";

    public List<Client> addClients(List<Client> listClient) throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Client> actualList = null;
        List<Client> copylist = null;

        try {
            actualList = Arrays.asList(mapper.readValue(new File(DATA), Client[].class));
            copylist = new ArrayList<>(actualList);
            copylist.addAll(listClient);

            writer.writeValue(new File(DATA), copylist);
            return copylist;
        } catch (NotFoundException | IOException ex) {
            throw new FileNotFoundException("Data not found: " + DATA);
        }

    }

    public List<Client> getAllClients() throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList
                    (mapper.readValue(new File(DATA), Client[].class));

        } catch (NotFoundException | IOException ex) {
            throw new FileNotFoundException("Data not found: " + DATA);
        }
    }
}

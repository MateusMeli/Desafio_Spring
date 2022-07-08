package com.meli.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_spring.exception.NotFoundException;
import com.meli.desafio_spring.model.Ticket;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TicketRepository {

    private static final String DATA = "src/main/resources/ticket.json";

    public Ticket addTicket(Ticket ticket) throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Ticket> actualList = null;
        List<Ticket> copylist = null;

        try {
            actualList = Arrays.asList(mapper.readValue(new File(DATA), Ticket[].class));
            copylist = new ArrayList<>(actualList);
            copylist.add(ticket);

            writer.writeValue(new File(DATA), copylist);
            return ticket;

        } catch (Exception ex) {
            throw new FileNotFoundException("Data not found: " + DATA);
        }

    }

    public List<Ticket> getAllTickets() throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        List<Ticket> lista = new ArrayList<>();
        try {
            lista.addAll(Arrays.asList
                    (mapper.readValue(new File(DATA), Ticket[].class)));

        } catch (NotFoundException | IOException ex) {
            throw new FileNotFoundException("Data not found: " + DATA);
        }
        return lista;
    }
}

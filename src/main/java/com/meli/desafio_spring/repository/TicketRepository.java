package com.meli.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_spring.model.Ticket;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TicketRepository {

    private static final String DATA = "src/main/resources/ticket.json";

    public Ticket addTicket(Ticket ticket) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Ticket> actualList = null;
        List<Ticket> copylist = null;

        try {
            actualList = Arrays.asList(mapper.readValue(new File(DATA), Ticket[].class));
            copylist = new ArrayList<>(actualList);
            copylist.add(ticket);

            writer.writeValue(new File(DATA), copylist);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return ticket;

    }

    public List<Ticket> getAllTickets() {
        ObjectMapper mapper = new ObjectMapper();
        List<Ticket> lista = new ArrayList<>();
        try {
            lista.addAll(Arrays.asList
                    (mapper.readValue(new File(DATA), Ticket[].class)));

        } catch (Exception ex) {

        }
        return lista;
    }
}

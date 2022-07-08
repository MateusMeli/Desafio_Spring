package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ClientDto;
import com.meli.desafio_spring.exception.NotFoundException;
import com.meli.desafio_spring.model.Client;
import com.meli.desafio_spring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    ClientRepository repo;

    @Override
    public List<ClientDto> addClients(List<Client> listClient) throws FileNotFoundException {

        return repo.addClients(listClient).stream().map(c -> ClientDto
                        .builder()
                        .clientId(c.getClientId())
                        .name(c.getName())
                        .state(c.getState()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> getAllClients(
            String name,
            String state,
            Long cpf
    ) throws FileNotFoundException {

        List<Client> listClient = repo.getAllClients();

        listClient = clientFilters(name, state, cpf, listClient);


        return listClient.stream()
                .map(c -> ClientDto
                        .builder()
                        .clientId(c.getClientId())
                        .name(c.getName())
                        .state(c.getState()).build())
                .collect(Collectors.toList());

    }

    private List<Client> clientFilters(
            String name,
            String state,
            Long cpf,
            List<Client> listClient
    ) {

        List<Client> clientListFilter = new ArrayList<>();

        clientListFilter.addAll(listClient);

        if (name != null) {
            clientListFilter = clientListFilter
                    .stream()
                    .filter(c -> c.getName().equals(name))
                    .collect(Collectors.toList());
            if(clientListFilter.isEmpty()) {
                throw new NotFoundException("Name Not found: " + name);
            }
        }
        if (state != null) {
            clientListFilter = clientListFilter
                    .stream()
                    .filter(f -> f.getState().equals(state))
                    .collect(Collectors.toList());
            if(clientListFilter.isEmpty()) {
                throw new NotFoundException("State Not found: " + state);
            }
        }
        if (cpf != null) {
            clientListFilter = clientListFilter
                    .stream()
                    .filter(p -> p.getCpf() == cpf)
                    .collect(Collectors.toList());
            if(clientListFilter.isEmpty()) {
                throw new NotFoundException("CPF Not found: " + cpf);
            }
        }

        return clientListFilter;
    }

    public List<Client> sort(List<Client> list, Integer order) {
        switch (order) {
            case 0:
                list.sort(Comparator.comparing(Client::getName));
                break;
            case 1:
                list.sort(Comparator.comparing(Client::getState).reversed());
                break;
            default:
                throw new NotFoundException("This parameter does not exist: " + order);
        }
        return list;
    }
}

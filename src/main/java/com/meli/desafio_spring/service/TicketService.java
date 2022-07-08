package com.meli.desafio_spring.service;

import com.meli.desafio_spring.model.ProductPurchaseRequest;
import com.meli.desafio_spring.model.Ticket;

import java.io.FileNotFoundException;

public interface TicketService {
    Ticket purchaseRequest(ProductPurchaseRequest purchaseObject) throws FileNotFoundException;
}

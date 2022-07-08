package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.model.ProductPurchaseRequest;
import com.meli.desafio_spring.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket purchaseRequest(ProductPurchaseRequest purchaseObject);
}

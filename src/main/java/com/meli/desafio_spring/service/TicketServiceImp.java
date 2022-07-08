package com.meli.desafio_spring.service;

import com.meli.desafio_spring.dto.ProductDto;
import com.meli.desafio_spring.dto.ProductDtoPurchase;
import com.meli.desafio_spring.model.Product;
import com.meli.desafio_spring.model.ProductPurchaseRequest;
import com.meli.desafio_spring.model.Ticket;
import com.meli.desafio_spring.repository.ProductRepository;
import com.meli.desafio_spring.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImp implements TicketService {
    @Autowired
    private TicketRepository repoTicket;

    @Autowired
    private ProductRepository repoProduct;

    @Override
    public Ticket purchaseRequest(ProductPurchaseRequest purchaseObject) {
        Ticket ticket;
        double totalTicket = 0;
        List<Product> listProducts = repoProduct.getAllProducts();
        List<Product> listProductsTicket = new ArrayList<>();

        purchaseObject.getProductPurchaseRequest().forEach(pdto -> {
                    if (findProductById(listProducts, pdto).getQuantity() < pdto.getQuantity()) {
                        throw new RuntimeException("Quantidade desejada na compra excede ao estoque.");
                    }

                    listProductsTicket.add(listProducts.stream()
                            .filter(p -> p.getProductId() == pdto.getProductId())
                            .map(p -> {
                                        p.setQuantity(pdto.getQuantity());
                                        return p;
                                    }
                            )
                            .findFirst().get());
                }
        );

        totalTicket = purchaseObject.getProductPurchaseRequest().stream()
                .mapToDouble(p -> p.getQuantity() * findPriceProductForMultiply(p.getProductId(), listProducts)).sum();

        ticket = Ticket.builder()
                .id(repoTicket.getAllTickets().size())
                .listaProdutos(listProductsTicket)
                .total(totalTicket)
                .build();

        ticket = repoTicket.addTicket(ticket);

        repoProduct.updateProductStock(purchaseObject);

        return ticket;
    }

    private double findPriceProductForMultiply(long productId, List<Product> listProducts) {
        return listProducts.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst().get().getPrice();
    }

    private Product findProductById(List<Product> listProducts, ProductDtoPurchase pdto) {
        return listProducts.stream().filter(p -> p.getProductId() == pdto.getProductId()).findFirst().get();
    }
}

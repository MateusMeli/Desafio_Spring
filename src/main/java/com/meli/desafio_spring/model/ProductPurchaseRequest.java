package com.meli.desafio_spring.model;

import com.meli.desafio_spring.dto.ProductDtoPurchase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductPurchaseRequest {
    List<ProductDtoPurchase> productPurchaseRequest;
}

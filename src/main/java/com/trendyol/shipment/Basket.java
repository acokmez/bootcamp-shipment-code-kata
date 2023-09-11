package com.trendyol.shipment;

import com.trendyol.services.ShipmentService;

import java.util.List;

public class Basket {

    private final ShipmentService shipmentService = new ShipmentService();
    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        return shipmentService.decideShipmentSize(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

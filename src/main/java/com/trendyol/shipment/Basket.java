package com.trendyol.shipment;

import com.trendyol.services.ShipmentSizeCalculatorService;

import java.util.List;

public class Basket {

    private final ShipmentSizeCalculatorService shipmentSizeCalculatorService = new ShipmentSizeCalculatorService();
    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        return shipmentSizeCalculatorService.calculate(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

package com.trendyol.shipment;

import java.util.EnumMap;
import java.util.List;

import static com.trendyol.shipment.ShipmentSize.*;

public class Basket {

    private static final int BASKET_THRESHOLD_VALUE = 3;
    private List<Product> products;

    public ShipmentSize getShipmentSize() {

        EnumMap<ShipmentSize, Integer> productsMap = getProductsMap(products);

        if (hasContainsXlargeMoreThanThreshold(productsMap)) {
            return X_LARGE;
        }

        for (ShipmentSize key : productsMap.keySet()) {
            if (productsMap.get(key) >= BASKET_THRESHOLD_VALUE) {
                return key.getNext();
            }
        }

        return getLargestSize(products);

    }

    private boolean hasContainsXlargeMoreThanThreshold(EnumMap<ShipmentSize, Integer> productsMap) {
        return productsMap.containsKey(X_LARGE) && productsMap.get(X_LARGE) >= BASKET_THRESHOLD_VALUE;
    }

    private EnumMap<ShipmentSize, Integer> getProductsMap(List<Product> products) {
        EnumMap<ShipmentSize, Integer> productsMap = new EnumMap<>(ShipmentSize.class);

        for (Product product : products) {
            ShipmentSize shipmentSize = product.getSize();
            if (productsMap.containsKey(shipmentSize)) {
                productsMap.put(shipmentSize, productsMap.get(shipmentSize) + 1);
            } else {
                productsMap.put(shipmentSize, 1);
            }
        }

        return productsMap;
    }

    private ShipmentSize getLargestSize(List<Product> products) {
        ShipmentSize largestSize = ShipmentSize.SMALL;
        for (Product product : products) {
            if (product.getSize().equals(MEDIUM)) {
                largestSize = MEDIUM;
            }
            if (product.getSize().equals(LARGE)) {
                largestSize = LARGE;
            }
        }
        return largestSize;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

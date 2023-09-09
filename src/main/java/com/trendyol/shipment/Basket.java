package com.trendyol.shipment;

import java.util.List;

import static com.trendyol.shipment.ShipmentSize.*;

public class Basket {

    private List<Product> products;

    private static final int basketThresholdValue = 3;

    public ShipmentSize getShipmentSize() {

        if (hasContainsXlarge(products)) {
            return ShipmentSize.X_LARGE;
        }

        if (allProductsSameSize(products)) {
            return increaseSize(products.get(0).getSize());
        }

        if (products.size() < basketThresholdValue) {
            return getLargestSize(products);
        }

        return getLargestSize(products);

    }

    private boolean allProductsSameSize(List<Product> products) {
        ShipmentSize firstSize = products.get(0).getSize();
        for (Product product : products) {
            if (product.getSize().equals(firstSize)) {
                return true;
            }
        }
        return false;
    }

    private static ShipmentSize getLargestSize(List<Product> products) {
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

    private boolean hasContainsXlarge(List<Product> products) {

        for (Product product : products) {
            if (product.getSize().equals(ShipmentSize.X_LARGE)) {
                return true;
            }
        }
        return false;
    }

    private static ShipmentSize increaseSize(ShipmentSize size) {
        if (size.equals(SMALL)) {
            return ShipmentSize.MEDIUM;
        } else if (size == ShipmentSize.MEDIUM) {
            return ShipmentSize.LARGE;
        } else if (size == ShipmentSize.LARGE) {
            return ShipmentSize.X_LARGE;
        } else {
            return ShipmentSize.X_LARGE;
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

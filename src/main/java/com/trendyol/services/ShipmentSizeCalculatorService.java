package com.trendyol.services;

import com.trendyol.shipment.Product;
import com.trendyol.shipment.ShipmentSize;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.trendyol.shipment.ShipmentSize.LARGE;
import static com.trendyol.shipment.ShipmentSize.MEDIUM;

public class ShipmentSizeCalculatorService {

    private static final int BASKET_THRESHOLD_VALUE = 3;

    public ShipmentSizeCalculatorService() {
        // intentionally empty
    }

    public ShipmentSize calculate(List<Product> products) {
        EnumMap<ShipmentSize, Integer> shipmentSizeMap = createShipmentSizeMap(products);

        for (ShipmentSize key : shipmentSizeMap.keySet()) {
            if (isAboveOrEqualsThreshold(shipmentSizeMap, key)) {
                return key.getNext();
            }
        }

        return findLargestSize(shipmentSizeMap);
    }

    private boolean isAboveOrEqualsThreshold(EnumMap<ShipmentSize, Integer> shipmentSizeMap, ShipmentSize key) {
        return shipmentSizeMap.containsKey(key) && shipmentSizeMap.get(key) >= BASKET_THRESHOLD_VALUE;
    }

    private ShipmentSize findLargestSize(EnumMap<ShipmentSize, Integer> shipmentSizeMap) {
        ShipmentSize largestSize = ShipmentSize.SMALL;
        for (ShipmentSize shipmentSize : shipmentSizeMap.keySet()) {
            if (shipmentSize.equals(MEDIUM)) {
                largestSize = MEDIUM;
            }
            if (shipmentSize.equals(LARGE)) {
                largestSize = LARGE;
            }
        }
        return largestSize;
    }

    private EnumMap<ShipmentSize, Integer> createShipmentSizeMap(List<Product> products) {

        return products.stream()
                .collect(Collectors.groupingBy(Product::getSize,
                        () -> new EnumMap<>(ShipmentSize.class),
                        Collectors.summingInt(product -> 1)));
    }
}

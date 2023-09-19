package com.trendyol.services;

import com.trendyol.shipment.Product;
import com.trendyol.shipment.ShipmentSize;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShipmentSizeCalculatorService {

    private static final int BASKET_PRODUCT_THRESHOLD_VALUE = 3;

    public ShipmentSizeCalculatorService() {
        // intentionally empty
    }

    public ShipmentSize calculate(List<Product> products) {
        EnumMap<ShipmentSize, Integer> shipmentSizeMap = createShipmentSizeMap(products);

        return shipmentSizeMap.keySet().stream()
                .filter(key -> isAboveOrEqualsThreshold(shipmentSizeMap, key))
                .findFirst()
                .map(ShipmentSize::getNext)
                .orElseGet(() -> findLargestSize(shipmentSizeMap));
    }

    private boolean isAboveOrEqualsThreshold(EnumMap<ShipmentSize, Integer> shipmentSizeMap, ShipmentSize key) {
        return shipmentSizeMap.containsKey(key) && shipmentSizeMap.get(key) >= BASKET_PRODUCT_THRESHOLD_VALUE;
    }

    private ShipmentSize findLargestSize(EnumMap<ShipmentSize, Integer> shipmentSizeMap) {
        return shipmentSizeMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .max(Comparator.naturalOrder())
                .orElse(ShipmentSize.SMALL);
    }

    private EnumMap<ShipmentSize, Integer> createShipmentSizeMap(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getSize,
                        () -> new EnumMap<>(ShipmentSize.class),
                        Collectors.summingInt(product -> 1)));
    }
}

package com.trendyol.shipment;

import java.util.Arrays;
import java.util.List;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;
    
    public ShipmentSize getNext() {
        if (this.equals(X_LARGE)) {
            return  X_LARGE;
        }
        ShipmentSize[] values = ShipmentSize.values();
        List<ShipmentSize> shipmentSizes = Arrays.asList(values);
        int indexOf = shipmentSizes.indexOf(this);
        return shipmentSizes.get(indexOf + 1);
    }
}

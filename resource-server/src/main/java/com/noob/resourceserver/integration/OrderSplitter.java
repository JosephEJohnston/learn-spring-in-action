package com.noob.resourceserver.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderSplitter {

    public Collection<Object> splitOrderIntoParts(PurchaseOrder po) {
        List<Object> parts = new ArrayList<>();
        parts.add(po.getBillingInfo());
        parts.add(po.getLineItems());
        return parts;
    }
}

package com.noob.resourceserver.integration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseOrder {
    private BillingInfo billingInfo;

    private List<LineItem> lineItems;
}

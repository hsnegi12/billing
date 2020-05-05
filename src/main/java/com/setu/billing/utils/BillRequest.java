package com.setu.billing.utils;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillRequest {

    private CustomerIdentifiers customerIdentifiers;

    @Getter
    @Setter
    public class CustomerIdentifiers {
        private String attributeName;
        private String attributeValue;
    }
}

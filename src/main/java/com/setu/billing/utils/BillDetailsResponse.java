package com.setu.billing.utils;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDetailsResponse {
    private String billFetchStatus;
    private List<BillResponse> listOfBillResponse;
}

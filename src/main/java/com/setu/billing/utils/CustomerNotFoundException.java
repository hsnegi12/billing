package com.setu.billing.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNotFoundException extends RuntimeException {

    private ErrorCodeFormat errorCodeFormat;

    public CustomerNotFoundException(ErrorCodeFormat errorCodeFormat) {
        super(String.valueOf(errorCodeFormat));
    }

}

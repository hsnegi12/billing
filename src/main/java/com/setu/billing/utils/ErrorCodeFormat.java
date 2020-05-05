package com.setu.billing.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorCodeFormat {
    private String code;
    private String title;
    private String traceID;
    private String description;
    private String param;
    private String docURL;
}

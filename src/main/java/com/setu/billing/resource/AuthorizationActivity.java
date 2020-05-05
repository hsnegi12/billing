package com.setu.billing.resource;

import com.setu.billing.service.AuthorizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationActivity {

    @GetMapping(value = "/get")
    public ResponseEntity<String> getAuthorizationToken() {
        return new ResponseEntity<>(AuthorizationService.yieldBearerToken(), HttpStatus.OK);
    }
}

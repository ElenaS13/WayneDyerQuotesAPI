package com.e.sb;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Void> redirectToDocumentation() {
        URI documentationUri = URI.create("https://documenter.getpostman.com/view/2722958/2s93sjTTct");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(documentationUri);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}


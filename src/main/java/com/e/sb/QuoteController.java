package com.e.sb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes() {
        QuoteCSVReader csvReader = new QuoteCSVReader("/Users/ee/Documents/Code/SB/src/main/resources/quotes.csv");
        List<Quote> quotes = csvReader.readQuotes();
        return ResponseEntity.ok(quotes);
    }
}

package com.e.sb;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    // Track request counts per client
    private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes(HttpServletRequest request) {
        String clientIp = getClientIp(request);

        // Check request count for the client
        int count = requestCounts.getOrDefault(clientIp, 0);
        if (count >= 10) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        // Increment the request count
        requestCounts.put(clientIp, count + 1);

        // Proceed with processing the request and returning the quotes
        QuoteCSVReader csvReader = new QuoteCSVReader(getClass().getResourceAsStream("/quotes.csv").toString());
        List<Quote> quotes = csvReader.readQuotes();
        return ResponseEntity.ok(quotes);
    }

    // Helper method to extract client IP from request
    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }


    // Reset request counts at the start of each time window
    @Scheduled(fixedDelay = 3600000) // Runs every hour
    public void resetRequestCounts() {
        requestCounts.clear();
    }

}

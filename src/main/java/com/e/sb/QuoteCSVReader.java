package com.e.sb;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuoteCSVReader {
    private String filePath;

    public QuoteCSVReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Quote> readQuotes() {
        List<Quote> quotes = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource("/quotes.csv").getInputStream()))) {
            reader.skip(1);
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                Long id = Long.valueOf(row[0]);
                String quoteText = row[1];

                Quote quote = new Quote(id, quoteText);
                quotes.add(quote);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return quotes;
    }
}

package com.e.sb;

import com.e.sb.Quote;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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

        try (InputStream inputStream = getClass().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            CSVReader csvReader = new CSVReader(reader);
            csvReader.skip(1);
            List<String[]> rows = csvReader.readAll();

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

    private List<Quote> retrieveQuotesByWD() {
        QuoteCSVReader csvReader = new QuoteCSVReader("/quotes.csv");
        return csvReader.readQuotes();
    }
}

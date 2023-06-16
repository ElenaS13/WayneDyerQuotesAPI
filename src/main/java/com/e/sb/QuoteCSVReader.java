package com.e.sb;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuoteCSVReader {
    private String filePath;

    public QuoteCSVReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Quote> readQuotes() {
        List<Quote> quotes = new ArrayList<>();


        try (CSVReader reader = new CSVReader(new FileReader("/Users/ee/Documents/Code/SB/src/main/resources/quotes.csv"))) {
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

    private List<Quote> retrieveQuotesByWD() {
        QuoteCSVReader csvReader = new QuoteCSVReader("/Users/ee/Documents/Code/SB/src/main/resources/quotes.csv");
        return csvReader.readQuotes();
    }

}

package com.e.sb;

import com.google.firebase.database.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final DatabaseReference quotesRef;

    public QuoteController() {
        this.quotesRef = FirebaseDatabase.getInstance().getReference("quotes");
    }

    @PostMapping
    public Quote addQuote(@RequestBody Quote quote) {
        DatabaseReference newQuoteRef = quotesRef.push();
        String key = newQuoteRef.getKey();
        quote.setId(key);
        newQuoteRef.setValueAsync(quote);
        return quote;
    }

    @PostMapping("/batch")
    public List<Quote> addQuotes(@RequestBody List<Quote> quotes) {
        List<Quote> addedQuotes = new ArrayList<>();

        for (Quote quote : quotes) {
            DatabaseReference newQuoteRef = quotesRef.push();
            String key = newQuoteRef.getKey();
            quote.setId(key);
            newQuoteRef.setValueAsync(quote);
            addedQuotes.add(quote);
        }

        return addedQuotes;
    }


    @GetMapping
    public CompletableFuture<List<Quote>> getAllQuotes() {
        CompletableFuture<List<Quote>> future = new CompletableFuture<>();

        System.out.println("Registering ValueEventListener");
        quotesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Quote> quotes = new ArrayList<>();
                for (DataSnapshot quoteSnapshot : dataSnapshot.getChildren()) {
                    Quote quote = quoteSnapshot.getValue(Quote.class);
                    quotes.add(quote);
                }
                System.out.println("Retrieved quotes: " + quotes);

                future.complete(quotes);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future;
    }
}

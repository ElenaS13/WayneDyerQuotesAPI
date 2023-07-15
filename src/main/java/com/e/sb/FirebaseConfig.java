package com.e.sb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    private static boolean firebaseAppInitialized = false;

    @Bean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        if (!firebaseAppInitialized) {
            String jsonCredentials = System.getenv("FIREBASE_JSON");
            String databaseUrl = System.getenv("FIREBASE_DATABASE_URL");

            if (jsonCredentials == null || jsonCredentials.isEmpty()) {
                throw new IllegalArgumentException("Firebase JSON credentials not found. Please set the FIREBASE_JSON environment variable.");
            }
            if (databaseUrl == null || databaseUrl.isEmpty()) {
                throw new IllegalArgumentException("Firebase Database URL not found. Please set the FIREBASE_DATABASE_URL environment variable.");
            }

            ByteArrayInputStream credentialsStream = new ByteArrayInputStream(jsonCredentials.getBytes(StandardCharsets.UTF_8));

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(credentialsStream))
                    .setDatabaseUrl(databaseUrl)
                    .build();

            FirebaseApp.initializeApp(options);
            firebaseAppInitialized = true;
        }

        return FirebaseDatabase.getInstance();
    }
}

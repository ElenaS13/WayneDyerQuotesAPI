package com.e.sb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    
    private static boolean firebaseAppInitialized = false;

    @Bean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        if (!firebaseAppInitialized) {
            String configPath = System.getenv("FIREBASE_CONFIG_PATH");
            String databaseUrl = System.getenv("FIREBASE_DATABASE_URL");
            System.out.println("FIREBASE_CONFIG_PATH: " + configPath);
            System.out.println("FIREBASE_DATABASE_URL: " + databaseUrl);

            // Check if running on Heroku and adjust file path accordingly
            if (System.getenv("DYNO") != null && configPath != null) {
                configPath = "target/classes/" + configPath;
            }

            FileInputStream serviceAccount = new FileInputStream(configPath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(databaseUrl)
                    .build();

            FirebaseApp.initializeApp(options);
            firebaseAppInitialized = true;
        }

        return FirebaseDatabase.getInstance();
    }
}
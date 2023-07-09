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

    @Bean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/quotes-api-e6c3c-firebase-adminsdk-ifg0s-52c2bc8944.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://quotes-api-e6c3c-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
        return FirebaseDatabase.getInstance();
    }
}
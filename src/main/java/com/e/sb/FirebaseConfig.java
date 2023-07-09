package com.e.sb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    // ...

    @Bean
    public FirebaseDatabase firebaseDatabase() throws IOException {
        Dotenv dotenv = Dotenv.load();
        String configPath = dotenv.get("FIREBASE_CONFIG_PATH");
        String databaseUrl = dotenv.get("FIREBASE_DATABASE_URL");
        FileInputStream serviceAccount = new FileInputStream(configPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();

        FirebaseApp.initializeApp(options);
        return FirebaseDatabase.getInstance();
    }


}

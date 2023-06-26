package com.e.sb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {
    private final Environment environment;

    public FirebaseConfig(Environment environment) {
        this.environment = environment;
    }
    @PostConstruct
    public void initialize() {
        try {


            String firebaseConfigJson = environment.getRequiredProperty("FIREBASE_CONFIG_JSON");


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(firebaseConfigJson.getBytes(StandardCharsets.UTF_8))))
                    .setDatabaseUrl("https://quotes-api-e6c3c-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

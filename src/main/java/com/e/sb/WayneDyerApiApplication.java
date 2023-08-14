package com.e.sb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class } )
@Import(FirebaseConfig.class)
public class WayneDyerApiApplication {
    public static void main(String[] args) {

        SpringApplication.run(WayneDyerApiApplication.class, args);
    }

}


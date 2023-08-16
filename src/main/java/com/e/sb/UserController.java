package com.e.sb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final DatabaseReference usersRef;

    public UserController(FirebaseDatabase firebaseDatabase) {
        this.usersRef = firebaseDatabase.getReference("users");
    }


    @PostMapping("/signup")
    public CompletableFuture<String> signUpUser(@RequestBody User user) {
        DatabaseReference newUserRef = usersRef.push();
        String key = newUserRef.getKey();

        String apiKey = generateRandomApiKey();

        //System.out.println("Generated API Key: " + apiKey); // Debug print statement


        user.setApiKey(apiKey);

        newUserRef.setValueAsync(user);

        return CompletableFuture.completedFuture("User signed up successfully");

    }

    private String generateRandomApiKey() {
        int length = 32;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder apiKey = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            apiKey.append(characters.charAt(index));
        }

        return apiKey.toString();
    }
}

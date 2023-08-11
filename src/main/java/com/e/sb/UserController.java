package com.e.sb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.web.bind.annotation.*;
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

        // Convert the key to Long and set it as the user's ID
        user.setId(key);  // Firebase keys are alphanumeric, so you can set them directly as strings

        newUserRef.setValueAsync(user);

        return CompletableFuture.completedFuture("User signed up successfully");
    }


    // You can add more methods here for retrieving and updating user data
}

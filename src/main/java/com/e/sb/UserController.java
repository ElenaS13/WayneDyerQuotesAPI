package com.e.sb;

import com.e.sb.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/signup")
    public CompletableFuture<String> signUpUser(@RequestBody User user) {
        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            // Update user profile with display name
            UserRecord.UpdateRequest updateRequest = new UserRecord.UpdateRequest(userRecord.getUid())
                    .setDisplayName(user.getDisplayName()); // Set additional user properties as needed

            FirebaseAuth.getInstance().updateUser(updateRequest);

            return CompletableFuture.completedFuture("User signed up successfully");
        } catch (Exception e) {
            // Handle errors, such as duplicate email addresses
            return CompletableFuture.completedFuture("User sign-up failed: " + e.getMessage());
        }
    }
}

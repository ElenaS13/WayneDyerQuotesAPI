package com.e.sb;

import com.e.sb.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService; // Make sure to import UserService

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/signup")
//    public CompletableFuture<String> signUpUser(@RequestBody User user) {
//        try {
//            FirebaseAuth auth = FirebaseAuth.getInstance();
//            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                    .setEmail(user.getEmail())
//                    .setPassword(user.getPassword());
//
//            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
//
//            // Generate API key for the user
//            String apiKey = generateRandomApiKey();
//            System.out.println("Generated API Key: " + apiKey);
//
//            // Update user profile with display name
//            UserRecord.UpdateRequest updateRequest = new UserRecord.UpdateRequest(userRecord.getUid())
//                    .setDisplayName(user.getDisplayName());
//
//            FirebaseAuth.getInstance().updateUser(updateRequest);
//
//            // Save the user to the Firebase Realtime Database
//            user.setId(userRecord.getUid()); // Set user ID
//
//            // Add the API key to the user object
//            user.setApiKey(apiKey);
//
//            userService.saveUser(user);
//
//            return CompletableFuture.completedFuture("User signed up successfully.");
//        } catch (Exception e) {
//            // Handle errors, such as duplicate email addresses
//            return CompletableFuture.completedFuture("User sign-up failed: " + e.getMessage());
//        }
//    }


    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUpUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            // Generate API key for the user
            String apiKey = generateRandomApiKey();
            System.out.println("Generated API Key: " + apiKey);

            // Update user profile with display name
            UserRecord.UpdateRequest updateRequest = new UserRecord.UpdateRequest(userRecord.getUid())
                    .setDisplayName(user.getDisplayName());

            FirebaseAuth.getInstance().updateUser(updateRequest);

            // Save the user to the Firebase Realtime Database
            user.setId(userRecord.getUid()); // Set user ID

            // Add the API key to the user object
            user.setApiKey(apiKey);

            userService.saveUser(user);

            response.put("message", "User signed up successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "User sign-up failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            UserRecord userRecord = auth.getUserByEmail(user.getEmail());

            if (isUserValid(user)) {
                response.put("message", "User logged in successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "Authentication failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.put("error", "User login failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private boolean isUserValid(User user) {
        // Implement your logic to verify user's credentials
        // You can use your user service to check if the email and password match
        // You should not retrieve the password from Firebase
        // Here's a simplified example, replace with your actual logic:

        // Assuming userService has a method to validate user credentials
        return userService.isValidCredentials(user.getEmail(), user.getPassword());
    }



    private String generateRandomApiKey() {
        int length = 32; // Length of the API key
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Characters to choose from
        StringBuilder apiKey = new StringBuilder();

        // Generate the API key
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            apiKey.append(characters.charAt(index));
        }

        return apiKey.toString();
    }


    @GetMapping("/current-user")
    public ResponseEntity<String> getCurrentUserId() {
        try {
            // Get the currently authenticated user's ID
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();

            return ResponseEntity.ok(userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unable to retrieve user ID: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/apikey")
    public ResponseEntity<String> getUserApiKey(@PathVariable String userId) {
        try {
            // Assuming you have a way to fetch the API key for the given user ID
            String apiKey = String.valueOf(userService.getApiKey(userId));
            if (apiKey != null) {
                return ResponseEntity.ok(apiKey);
            } else {
                return ResponseEntity.notFound().build(); // Return a 404 if API key is not found
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unable to retrieve API key: " + e.getMessage());
        }
    }


}
package com.e.sb;

import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    private DatabaseReference usersRef;

    public UserService() {
        usersRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public void saveUser(User user) {
        try {
            usersRef.child(user.getId().toString()).setValueAsync(user);
        } catch (DatabaseException e) {
            // Handle the database write error, log it, or take appropriate action
            e.printStackTrace();
        }
    }

    public CompletableFuture<User> getUserByUsername(String username) {
        CompletableFuture<User> futureUser = new CompletableFuture<>();

        Query query = usersRef.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    futureUser.complete(user);
                    return;
                }
                // If no matching user is found, complete with null
                futureUser.complete(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                futureUser.completeExceptionally(databaseError.toException());
            }
        });

        return futureUser;
    }

    public CompletableFuture<String> getApiKey(String userId) {
        CompletableFuture<String> apiKeyFuture = new CompletableFuture<>();

        DatabaseReference userRef = usersRef.child(userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    String apiKey = user.getApiKey();
                    apiKeyFuture.complete(apiKey);
                } else {
                    apiKeyFuture.complete(null); // User not found
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                apiKeyFuture.completeExceptionally(databaseError.toException());
            }
        });

        return apiKeyFuture;
    }


    public boolean isValidCredentials(String email, String password) {

        return true;
    }
}

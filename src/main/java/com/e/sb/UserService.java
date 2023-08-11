package com.e.sb;

import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    private DatabaseReference usersRef;

    public UserService() {
        usersRef = FirebaseDatabase.getInstance().getReference("users"); // "users" is the name of the node where you want to store users
    }

    public void saveUser(User user) {
        try {
            usersRef.child(user.getId().toString()).setValueAsync(user);
        } catch (DatabaseException e) {

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
                    return;  // Assuming username is unique, so we can return after finding the first match
                }
                // If no matching user is found, complete with null
                futureUser.complete(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                futureUser.completeExceptionally(databaseError.toException());
            }
        });

        // Return the CompletableFuture immediately
        return futureUser;
    }


}

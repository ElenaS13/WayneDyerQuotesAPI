package com.e.sb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpRequest signUpRequest) {

        // Create a new User object from the sign-up request
        User newUser = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

        // Save the user using the UserService
        userService.saveUser(newUser);

        // Return a success response
        return ResponseEntity.ok("User signed up successfully");
    }
}

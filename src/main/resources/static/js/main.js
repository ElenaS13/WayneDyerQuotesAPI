document.addEventListener("DOMContentLoaded", () => {
const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "YOUR_AUTH_DOMAIN",
  databaseURL: "YOUR_DATABASE_URL",
  projectId: "YOUR_PROJECT_ID",
  storageBucket: "YOUR_STORAGE_BUCKET",
  messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
  appId: "YOUR_APP_ID"
};

firebase.initializeApp(firebaseConfig);

// Initialize Firebase with your Firebase project configuration (already done in main.js)

// Get references to HTML elements
const signupForm = document.querySelector("#signup-form");
const loginForm = document.querySelector("#login-form");
const emailInput = document.querySelector("#email");
const passwordInput = document.querySelector("#password");

// Add an event listener for the signup form
signupForm.addEventListener("submit", (e) => {
    e.preventDefault(); // Prevent the form from submitting normally

    const email = emailInput.value;
    const password = passwordInput.value;

    // send a POST request to the "/signup" endpoint with email and password

    fetch("/api/users/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({email, password})
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        alert(data.message);
    })
    .catch(error=> {
        console.error("Error:", error);
    });
    });
});


//
//// Add an event listener for the login form
//loginForm.addEventListener("submit", (e) => {
//    e.preventDefault(); // Prevent the form from submitting normally
//
//    const email = emailInput.value;
//    const password = passwordInput.value;
//
//    // Sign in the user with email and password
//    firebase.auth().signInWithEmailAndPassword(email, password)
//        .then((userCredential) => {
//            // User signed in successfully
//            const user = userCredential.user;
//            console.log("User signed in:", user);
//            // You can add further actions here, like redirecting to a dashboard or displaying a welcome message.
//        })
//        .catch((error) => {
//            // Handle errors during login
//            const errorCode = error.code;
//            const errorMessage = error.message;
//            console.error("Login error:", errorCode, errorMessage);
//            // You can display an error message to the user.
//        });
//});
//
//});



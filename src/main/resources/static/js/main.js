document.addEventListener("DOMContentLoaded", () => {

    let userId;

    const firebaseConfig = {
        apiKey: "AIzaSyBwl133lL3sitwY4QihAyd9RO_Wm1X1WAc",
        authDomain: "quotes-api-e6c3c.firebaseapp.com",
        databaseURL: "https://quotes-api-e6c3c-default-rtdb.firebaseio.com",
        projectId: "quotes-api-e6c3c",
        storageBucket: "quotes-api-e6c3c.appspot.com",
        messagingSenderId: "748451268357",
        appId: "1:748451268357:web:8c3af7275ac1ab7ca8596c"
    };

    firebase.initializeApp(firebaseConfig);


    // Get references to HTML elements
    const signupForm = document.querySelector("#signup-form");
    const loginForm = document.querySelector("#login-form");
    const emailInput = document.querySelector("#email");
    const passwordInput = document.querySelector("#password");
    const loginEmailInput = document.querySelector("#login-email");
    const loginPasswordInput = document.querySelector("#login-password");

    // Add an event listener for the signup form
    signupForm.addEventListener("submit", (e) => {
        e.preventDefault(); // Prevent the form from submitting normally

        const email = emailInput.value;
        const password = passwordInput.value;


        fetch("/api/users/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                alert(data.message);

                if (data.message === "User signed up successfully") {
                    // Redirect to dashboard.html

                    window.location.href = "dashboard.html";
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    });




    // Add an event listener for the login form
    loginForm.addEventListener("submit", (e) => {
        e.preventDefault(); // Prevent the form from submitting normally

        const email = loginEmailInput.value;
        const password = loginPasswordInput.value;

       fetch("/api/users/login", {
                   method: "POST",
                     headers: {
                            "Content-Type": "application/json"
                        },
                     body: JSON.stringify({ email, password })

               })
                   .then(response => response.json())
                   .then(data => {
                       console.log(data);
                       alert(data.message);

                       if (data.message === "User logged in successfully") {
                           // Redirect to dashboard.html

                           window.location.href = "dashboard.html";
                       }
                   })
                   .catch(error => {
                       console.error("Error:", error);
                   });
    });
});

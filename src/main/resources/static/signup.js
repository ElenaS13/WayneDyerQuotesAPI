const signupForm = document.getElementById("signup-form");
const messageElement = document.getElementById("message");

signupForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Prevent default form submission

    const email = signupForm.email.value;
    const password = signupForm.password.value;

    try {
        const response = await fetch('/api/users/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });


        if (response.ok) {
            messageElement.textContent = "Thank you for signing up!";
            signupForm.reset(); // Reset the form input fields

        } else {
            const errorMessage = await response.text();
            messageElement.textContent = `Error: ${errorMessage}`;
        }
    } catch (error) {
        messageElement.textContent = `Error: ${error.message}`;
    }
});

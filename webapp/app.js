document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const loginData = {
        username: username,
        password: password
    };

    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();  // Parse the response as JSON
        } else {
            throw new Error("Login failed");
        }
    })
    .then(data => {
        // Log the entire response
        console.log("Login Response:", data);

        // Extract and log individual values
        console.log("JWT Token:", data.jwtToken);
        console.log("Username:", data.username);

        // Iterate through the roles array
        data.roles.forEach((role, index) => {
            console.log(`Role ${index + 1}:`, role);
        });

        // Redirect to /home
        window.location.href = "/home";
    })
    .catch(error => {
        console.error("Error during login:", error);
        document.getElementById("error-message").style.display = "block";
    });
});

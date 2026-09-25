document.getElementById("loginForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const message = document.getElementById("message");

    message.textContent = "Signing in...";
    message.style.color = "#777";

    try {

        const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        const result = await response.text();

        if (response.ok) {

            localStorage.setItem("userName", result);
            localStorage.setItem("userEmail", email);

            window.location.href = "dashboard.html";

        } else {

            message.textContent = result || ("Error " + response.status);
            message.style.color = "#d33";

        }

    } catch (error) {

        message.textContent = "Server connection failed.";
        message.style.color = "#d33";

    }

});
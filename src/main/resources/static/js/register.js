document.getElementById("registerForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const message = document.getElementById("message");

    message.textContent = "Creating account...";
    message.style.color = "#777";

    try {

        const response = await fetch("/api/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: name,
                email: email,
                password: password
            })
        });

        const result = await response.text();

        if (response.ok) {

            message.textContent = "Account created successfully!";
            message.style.color = "green";

            setTimeout(() => {
                window.location.href = "login.html";
            }, 1000);

        } else {

            message.textContent = result || ("Error " + response.status);
            message.style.color = "#d33";

        }

    } catch (error) {

        message.textContent = "Server connection failed.";
        message.style.color = "#d33";

    }

});
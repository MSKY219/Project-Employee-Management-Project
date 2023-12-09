$(document).ready(function () {
    const loginBtn = $('.login-btn');

    loginBtn.on("click", () => {
        console.log("Button clicked");
        window.location.href = "http://localhost:8081/member";
    });
});

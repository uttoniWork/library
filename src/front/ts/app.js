var btnSignin = document.querySelector("#signin");
var btnSignup = document.querySelector("#signup");
var body = document.querySelector("body");
var element = document.getElementById("content");

btnSignin.addEventListener("click", function () {
    body.className = "sign-in-js";
});

btnSignup.addEventListener("click", function () {
    body.className = "sign-up-js";
});

function HideShow(id, inverse_id){
    document.getElementById(id).style.display = 'block';
    document.getElementById(inverse_id).style.display = 'none';
}

function login() {
    window.open('./index.html');
    window.close();
}

function create() {

    let url = 'http://localhost:15000/client';

    var form = document.getElementById("create");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", function (event) {
        event.preventDefault();

        const userName = document.getElementById("userName").value
        const email = document.getElementById("email").value
        const password = document.getElementById("password").value

        var clientRequest = {
          "userName": userName,
          "email": email,
          "password": password,
        };

        fetch(url, {
            method: 'POST',
            headers: {
                // 'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(clientRequest)
        })
            .then(response => response.json())
            .then(response => console.log(JSON.stringify(response)))

    });
    alert("usuario cadastrado");
}

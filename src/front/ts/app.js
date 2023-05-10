var btnSignin = document.querySelector("#signin");
var btnSignup = document.querySelector("#signup");
var body = document.querySelector("body");
var element = document.getElementById("content");

var loggedClient = {
    clientId: "",
    userName: ""
};

var outroClient;

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

function showPopup(img) {
    const popup = document.getElementById('popup');
    const popupImage = document.getElementById('popup-image');
    const popupTitle = document.getElementById('popup-title');

    popupImage.src = img.src;
    popupTitle.innerText = img.alt;

    popup.style.display = 'flex';
  }

  function closePopup() {
    const popup = document.getElementById('popup');

    popup.style.display = 'none';
  }




function showRegister(id){
    document.getElementById(id).style.display = 'block'
}









function createBook(id){

    document.getElementById(id).style.display = 'none'

    let url = 'http://localhost:15000/book';

    var form = document.getElementById("create");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", function (event) {
        event.preventDefault();

        const clientId = loggedClient.clientId
        const title = document.getElementById("book_name").value
        const author = document.getElementById("autor").value
        const coverImage = document.getElementById("image").value
        const gender = document.getElementById("genero").value
        const editor = document.getElementById("editora").value
        const releaseYear = document.getElementById("mes").value


        var clientRequest = {
          "clientId": clientId,
          "title": title,
          "coverImage": coverImage,
          "gender": gender,
          "editor": editor,
          "releaseYear": releaseYear
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
    });
    alert("usuario cadastrado");



}


function login() {

    var form = document.getElementById("login");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit",function (event) {
        event.preventDefault();

        const email = document.getElementById("emailLogin").value
        const password = document.getElementById("passwordLogin").value

        let url = 'http://localhost:15000/client/login?email=' + email + '&password=' + password;

        fetch(url, {
            method: 'GET',
            headers: {
                // 'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response =>  response.json())
            .then((json) => {
                loggedClient = json;
                window.open('./index.html');
                window.close();
            })
    });

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
    });
    alert("usuario cadastrado");
}
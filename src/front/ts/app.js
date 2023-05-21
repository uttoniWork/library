var btnSignin = document.querySelector("#signin");
var btnSignup = document.querySelector("#signup");
var body = document.querySelector("body");
var element = document.getElementById("content");

//////////////////////////////////////////////
var berserkerDiv = document.querySelector("#berserkerDiv");
var griffithDiv = document.querySelector("#griffithDiv");
berserkerDiv.addEventListener("click", function () {

    griffithDiv.style.display = 'block';
});
griffithDiv.addEventListener("click", function () {
    griffithDiv.style.display = 'none';
});

//////////////////////////////////////////////
var berserkerDiv = document.querySelector("#berserkerDiv");
var griffithDiv = document.querySelector("#griffithDiv");
berserkerDiv.addEventListener("click", function () {

    griffithDiv.style.display = 'block';
});
griffithDiv.addEventListener("click", function () {
    griffithDiv.style.display = 'none';
});

///////////////////////////////////////////////

function showPopup(img) {
    const popup = document.getElementById('popup');
    const popupImage = document.getElementById('popup-image');
    const popupTitle = document.getElementById('popup-title');

    popupImage.src = img.src;
    popupTitle.innerText = img.alt;
    popup.style.display = 'block';


}
var loggedClient = {
    clientId: "",
    username: ""
};

btnSignin.addEventListener("click", function () {
    body.className = "sign-in-js";
});

btnSignup.addEventListener("click", function () {
    body.className = "sign-up-js";
});


function getClient(){
    return localStorage.getItem("storageLoggedClient")
}
function saveClient(client){
    localStorage.setItem("storageLoggedClient", client);
}


function HideShow(id, inverse_id){
    document.getElementById(id).style.display = 'block';
    document.getElementById(inverse_id).style.display = 'none';
}


function showRegister(){
    document.getElementById('content').style.display = 'block'

    // console.log("criando livro")

    // // document.getElementById(idDiv).style.display = 'none'

    // // loggedClient = localStorage.getItem("storageLoggedClient");

    // let url = 'http://localhost:15000/book';

    // console.log("url: " + url)

    // var form = document.getElementById("createBook");

    // console.log("Form: " + form)
    // form === null || form === void 0 ? void 0 : form.addEventListener("submit",function (event) {
    //     event.preventDefault();

    //     console.log("entrei no form")

    //     const clientId = 2
    //     const title = document.getElementById("book_name").value
    //     const author = document.getElementById("autor").value
    //     // const coverImage = document.getElementById("image").value
    //     const coverImage = "foto"
    //     // const genres = document.getElementById("genero").value
    //     const genres = ["Fantasia"]
    //     const editor = document.getElementById("editora").value
    //     // const releaseYear = document.getElementById("mes").value
    //     const releaseYear = 2023

    //     var bookRequest = {
    //         "clientId": clientId,
    //         "title": title,
    //         "author": author,
    //         "coverImage": coverImage,
    //         "genres": genres,
    //         "editor": editor,
    //         "releaseYear": releaseYear
    //     };

    //     console.log("request enviado: " + JSON.stringify(bookRequest))

    //     fetch(url, {
    //         method: 'POST',
    //         headers: {
    //             // 'Accept': 'application/json',
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(bookRequest)
    //     })
    //         .then(response => {
    //             console.log(response.json())
    //         })
    // });
}

function createBook(){

    // console.log("criando livro")

     document.getElementById('content').style.display = 'none'

    // loggedClient = localStorage.getItem("storageLoggedClient");

    // let url = 'http://localhost:15000/book';

    // var form = document.getElementById("createBook");
    // form === null || form === void 0 ? void 0 : form.addEventListener("submit", function (event) {
    //     event.preventDefault();

    //     const clientId = loggedClient.clientId
    //     const title = document.getElementById("book_name").value
    //     const author = document.getElementById("autor").value
    //     // const coverImage = document.getElementById("image").value
    //     const coverImage = "foto"
    //     // const genres = document.getElementById("genero").value
    //     const genres = ["Fantasia"]
    //     const editor = document.getElementById("editora").value
    //     // const releaseYear = document.getElementById("mes").value
    //     const releaseYear = 2023

    //     var bookRequest = {
    //       "clientId": clientId,
    //       "title": title,
    //       "author": author,
    //       "coverImage": coverImage,
    //       "genres": genres,
    //       "editor": editor,
    //       "releaseYear": releaseYear
    //     };

    //     console.log(JSON.stringify(bookRequest))

    //     fetch(url, {
    //         method: 'POST',
    //         headers: {
    //             // 'Accept': 'application/json',
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(bookRequest)
    //     })
    //         .then(response => {
    //             console.log(response.json())
    //         })
    // });
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
                loggedClient = json
                saveClient(loggedClient);
                document.getElementById('clientName').textContent = loggedClient.username;
                window.open('./index.html');
                window.close();
            })
    });

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



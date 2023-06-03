
function getItem(){
    return localStorage.getItem(client)

    window.onload
}


function HideShow(id, inverse_id){
    document.getElementById(id).style.display = 'block';
    document.getElementById(inverse_id).style.display = 'none';
}

//////////////////////////   DON'T TOUCH IS WORKING BY MIRACLE           /////////////////////////////////////////////////////
var image = document.querySelector(".berserkerClass");
var popup = document.querySelector(".popup");
image.addEventListener("click", function () {

    popup.style.display = 'block';
});
popup.addEventListener("click", function () {
    popup.style.display = 'none';
});
////////////////////////////////////////////////////////////////////////////////
var imageList = document.querySelector(".berserkerClassList");
var popupList = document.querySelector(".popupList");
popupList.addEventListener("click", function () {

    popupList.style.display = 'block';
});
popupList.addEventListener("click", function () {
    popupList.style.display = 'none';
});
////////////////////////////////////////////////////////////////////////////////////
function showPopup(img) {
    const popup = document.getElementById('popupId');
    const popupImage = document.getElementById('popup-image');
    const popupTitle = document.getElementById('popup-title');

    popupImage.src = img.src;
    popupTitle.innerText = img.alt;

    popup.style.display = 'flex';
  }

  function closePopup() {
    const popup = document.getElementById('popupId');

    popup.style.display = 'none';
  }

  function showPopupList(img) {
    const popup = document.getElementById('popupListId');
    const popupImage = document.getElementById('popup-image-list');
    const popupTitle = document.getElementById('popup-title-list');

    popupImage.src = img.src;
    popupTitle.innerText = img.alt;

    popup.style.display = 'flex';
  }

  function closePopup() {
    const popup = document.getElementById('popupListId');

    popup.style.display = 'none';
  }

////////////////////////////////////////////////////////////////////////////////////////////////

function showRegister(id){
    document.getElementById(id).style.display = 'block';
}

/////////////////////////////////////////////////////////////////////////////////////////////////
function searchBooks(idDiv){
    var campo = document.getElementById("text-search");
    if (campo.value !== "") {
        document.getElementById(idDiv).style.display = 'block';
    }
    else{
        window.alert("Preencha o campo de pesquisa")
    }
}

function validaCampo(){
    var title = document.getElementById("book_name")
    var author = document.getElementById("autor")
    var coverImage = document.getElementById("image")
    var gender = document.getElementById("genero")
    var editor = document.getElementById("editora")
    var releaseYear = document.getElementById("mes")

    if (title.value !== '' && author.value !== '' && coverImage.value !== '' && gender.value !== '' && editor.value !== '' && releaseYear.value !== ''){
        createBook();
        document.getElementById('content').style.display = 'none';
    }
}

function createBook(){


    /*let url = 'http://localhost:15000/book';

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
          "author": author,
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
    });*/
    alert("Livro cadastrado");
}

//lista livros do cliente, deve ser carregado assim q a pagina carrega pra montar a lista de livros do cliente
function findBooksOfClient(){
    
    //var form = document.getElementById("login");
    
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", function (event) {
        event.preventDefault();

        const clientId = 1;

        let url = 'http://localhost:15000/book/clientId=' + clientId;

        fetch(url, {
            method: 'GET',
            headers: {
                // 'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
        .then(response =>  response.json())
        .then((json) => {
            //Do something
        })
    });
}

//pesquisa livros por genero
function findBooksOfGenre(){
    
    //var form = document.getElementById("login");
    
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", function (event) {
        event.preventDefault();

        const genreName = "Fantasia";

        let url = 'http://localhost:15000/book/genre/genreName=' + genreName;

        fetch(url, {
            method: 'GET',
            headers: {
                // 'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
        .then(response =>  response.json())
        .then((json) => {
            //Do something
        })
    });
}


//Chamado pelo botão de + quando for adicionar um livro q já existe à lista do cliente
function addBookToClient(){


    let url = 'http://localhost:15000/book/choose';

    //var form = document.getElementById("create");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", function (event) {
        event.preventDefault();

        const clientId = loggedClient.clientId
        const bookId = document.getElementById("bookId").value


        var clientBookRequest = {
          "clientId": clientId,
          "bookId": bookId
        };

        fetch(url, {
            method: 'POST',
            headers: {
                // 'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(clientBookRequest)
        })
            .then(response => response.json())
    });
}
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
    var form = document.getElementById("create");
    form === null || form === void 0 ? void 0 : form.addEventListener("submit", function (event) {
        event.preventDefault();
        var data = new FormData(form);
    });
    alert("usuario cadastrado");
    window.close();
    window.open('./index.html');
}

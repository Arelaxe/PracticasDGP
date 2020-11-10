errorLabel = document.getElementById("errorLabel");
errorLabel.addEventListener("load", errorRegistro());
function errorRegistro() {
    console.log("test");
    $('#errorModal').modal('show');
};
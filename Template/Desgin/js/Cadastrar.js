var attempt = 3; // Variable to count number of attempts.
// Below function Executes on click of login button.
function validar() {
    var username =document.getElementById("nome").value;   
    var password =document.getElementById("senha").value;
    var email =document.getElementById("email").value; 
    var cnpj =document.getElementById("cnpj").value;
    var job =document.getElementById("categoria").value;
    var button= document.getElementById("button").value;
                    if ( username == "Formget" && password == "formget#123"){
                    alert ("Login successfully");
                    window.location = "cadastro2.html"; // Redirecting to other page.
                    return false;
                    }
else{
attempt --;// Decrementing by one.
alert("You have left "+attempt+" attempt;");
// Disabling fields after 3 attempts.
if( attempt == 0){
var username = document.getElementById("nome").value;
var password = document.getElementById("senha").value;
var email = document.getElementById("email").value;
var cnpj = document.getElementById("cnpj").value;
var job = document.getElementById("categoria").value;
var button = document.getElementById("button").value;
return false;
}
}
}


/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function login(){
    let userObj = document.getElementById("user");
    let passwordObj = document.getElementById("password");
    console.log(userObj);
    console.log(passwordObj);
    if(userObj.value == "" || passwordObj.value == ""){
        userObj.style.border = "2px solid #e31919";
        passwordObj.style.border = "2px solid #e31919";
        userObj.style.background = "#de3e3e30";
        passwordObj.style.background = "#de3e3e30";
    }else{
        
    }
}

function register(){
    let nombreObj = document.getElementById("nombre");
    let apellidosObj = document.getElementById("apellidos");
    let correoObj = document.getElementById("correo");
    let dniObj = document.getElementById("dni");
    let userObj = document.getElementById("user");
    let passwordObj = document.getElementById("password");
    let repeatPasswordObj = document.getElementById("passwordRepeat");

    let inputFields = [nombreObj, apellidosObj, correoObj, dniObj, userObj, passwordObj, repeatPasswordObj];
    let valid = true;

    for (let i = 0; i < inputFields.length; i++) {
        if (inputFields[i].value === "") {
            inputFields[i].style.border = "2px solid #e31919";
            inputFields[i].style.background = "#de3e3e30";
            valid = false;
            
        } else {
            inputFields[i].style.border = ""; // Restaura el borde a su estado predeterminado
            inputFields[i].style.background = ""; // Restaura el fondo a su estado predeterminado
        }
    }
    if (passwordObj.value !== repeatPasswordObj.value || passwordObj.value == "") {
        passwordObj.style.border = "2px solid #e31919";
        passwordObj.style.background = "#de3e3e30";
        repeatPasswordObj.style.border = "2px solid #e31919";
        repeatPasswordObj.style.background = "#de3e3e30";
        valid = false;
    } else {
        // Si las contraseñas coinciden, restablece el estilo
        passwordObj.style.border = "";
        passwordObj.style.background = "";
        repeatPasswordObj.style.border = "";
        repeatPasswordObj.style.background = "";
    }

    if(valid){
        let form = document.getElementById("complete-register");
        form.submit();
    }
}

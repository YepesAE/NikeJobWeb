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
        let form = document.getElementById("login-form");
        form.submit();
    }
}

function register(){
    let nombreObj = document.getElementById("nombre");
    let apellidosObj = document.getElementById("apellidos");
    let correoObj = document.getElementById("correo");
    let dniObj = document.getElementById("dni");
    let userObj = document.getElementById("user");
    let lugarObj = document.getElementById("lugar");
    let passwordObj = document.getElementById("password");
    let repeatPasswordObj = document.getElementById("passwordRepeat");

    let inputFields = [nombreObj, apellidosObj, correoObj, dniObj, userObj, lugarObj, passwordObj, repeatPasswordObj];
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

function getCookie(name) {
        const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
        if (match) return match[2];
}
    
window.onload = function () {
        const ciudadPreferente = getCookie("lugar");
        const numConexiones = getCookie("numConexiones");

        document.getElementById("ciudadPreferente").innerText = "Ciudad Preferente: " + ciudadPreferente;
        document.getElementById("numConexiones").innerText = "Número de conexiones: " + numConexiones;
};

function openTest(testNumber) {
    let solBox = document.getElementsByClassName("sol");
    Array.from(solBox).forEach((box) => box.style.display = "none"); 
    checkTest().then(function (respuesta) {
        if (respuesta.trim() !== "abierto") {
            signalServer("abierto");
            let tests = document.getElementsByClassName("test-box");
            for (var i = 0; i < tests.length; i++) {
                if (i !== (testNumber - 1)) {
                    tests[i].style.display = "none";
                } else {
                    tests[i].style.width = "90%";
                    tests[i].style.height = "75vh";
                }
            }
            let answers = document.getElementsByClassName("quiz");
            for (var i = 0; i < answers.length; i++) {
                if (i !== (testNumber - 1)) {
                    answers[i].style.display = "none";
                } else {
                    answers[i].style.display = "block";
                }
            }
        }else{
            alert("Se ha bloqueado el acceso a los tests");
        }
    }).catch(function (error) {
        console.error(error);
    });
}

function checkAnswers(inicio, fin) {
    let nota = 0;
    for (var i = inicio; i <= fin; i++) {
        let options = document.getElementsByName("q"+i);
        for (var j = 0; j < options.length; j++) {
            if (options[j].checked && options[j].value === "true") {
                nota += 1;
            }
        }
    }
    console.log('la nota es de: '+ nota);
    let testsBox = document.getElementsByClassName("test-box");
    console.log(testsBox);
    if(nota <= 3){
        Array.from(testsBox).forEach((box) => box.style.display = "none");  
        document.getElementById("not-added").style.display = "flex";
    }else if(nota <= 6){
        Array.from(testsBox).forEach((box) => box.style.display = "flex"); 
        testsBox[(fin/10)-1].style.display = "none";
        document.getElementById("not-added-yet").style.display = "flex";
        signalServer('cerrado');
    }else{
        Array.from(testsBox).forEach((box) => box.style.display = "none");
        document.getElementById("added").style.display = "flex";
        signalServer('cerrado');
    }

}

function checkTest() {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "checkTest", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    resolve(xhr.responseText);
                } else {
                    reject("Error al llamar al Servlet. Estado: " + xhr.status);
                }
            }
        };
        xhr.send();
    });
}

function signalServer(estado) {
    var xhr = new XMLHttpRequest();
    let respuesta = "";
    xhr.open("POST", "signalServer", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                respuesta = xhr.responseText;
            } else {
                console.error("Error al llamar al Servlet. Estado:", xhr.status);
            }
        }
    };
    console.log("estado="+estado);
    xhr.send("estado="+estado);
    return respuesta;
}

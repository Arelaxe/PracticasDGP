function validar_email(){
    var expresion = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;;

    if (expresion.test(document.getElementById("email").value)){
        document.getElementById("error_email").style.display = 'none';
    }
    else{
        document.getElementById("error_email").style.display = 'contents';
    }
}

function validar_pass(){
    var pass = document.getElementById("pass").value;

    if (pass.length < 6){
        document.getElementById("error_pass").style.display = 'contents';
    }
    else{
        document.getElementById("error_pass").style.display = 'none';
    }
}

function reestablecer(){
    document.getElementById('campo1').style.display = 'block';
    document.getElementById('campo2').style.display = 'none';
    document.getElementById('campo3').style.display = 'none';
    document.getElementById('campo4').style.display = 'none';
    document.getElementById('campo5').style.display = 'none';
    for(let i = 0 ; i < document.getElementsByClassName('miniimg').length ; i++){
        document.getElementsByClassName('miniimg').item(i).setAttribute('style',"display: none;");
    }
    document.getElementById('secuencia').setAttribute('content',"") ;
    puedoRegistrar();
}

function avanzar(zona,fragmento){
    if (zona > 0 && zona < 5){
        campoActual = "campo" + zona ;
        campoSiguiente = "campo" + (zona + 1) ;   

        document.getElementById(campoSiguiente).style.display = 'block';
        document.getElementById(campoActual).style.display = 'none';
    }
    
    miniimagenSeleccionada = "opcion" + (((zona - 1) * 4) + fragmento);
    document.getElementById(miniimagenSeleccionada).style.display = 'block' ;

    if(zona == 5){
        document.getElementById('campo5').style.display = 'none';
        extraerContrasena();
    }
}

function extraerContrasena(){
    password = "";

    for(let i = 0 ; i < document.getElementsByClassName('miniimg').length ; i++){
        if(document.getElementsByClassName('miniimg').item(i).getAttribute('style').includes("block")){
            password += document.getElementsByClassName('miniimg').item(i).getAttribute("alt");
        }
    }

    document.getElementById('secuencia').setAttribute('value',password);
    puedoRegistrar();
}

function puedoRegistrar(){
    /*¿Hay un nombre de usuario?*/
    usuarioValido = (document.getElementById("nombre").value.trim().length > 0) ;
    
    /*¿La contraseña se ha generado?*/
    contrasenaIntroducida = (document.getElementById("secuencia").getAttribute('value').trim().length > 0) ;

    /*Ahora sí, tema botón*/
    (usuarioValido && contrasenaIntroducida) ? document.getElementById("botonRegistro").disabled = false : document.getElementById('botonRegistro').disabled = true ;

    /*Añadimos unas indicaciones*/
    usuarioValido ? document.getElementById("error_nombre").style.display = 'none' : document.getElementById("error_nombre").style.display = 'block';
    contrasenaIntroducida ? document.getElementById("error_pass").style.display = 'none' : document.getElementById("error_pass").style.display = 'block';
}

function ayuda(){
    alert("La contraseña de un usuario se compone de 5 secuencias con 4 imágenes por secuencia,.\n Un usuario deberá elegir una de esas 4 en cada secuencia"
            + " para poder iniciar sesión.\n" + "El orden es: 1 figura, 1 fruta, 1 animal, 1 vehículo y 1 juguete.\n\n" + "Para crear la contraseña, vaya haciendo clic "
            + "en cada una de las 4 imágenes.\n Podrá ver la contraseña debajo, y si quiere cambiarla antes de crear el usuario,\n pulse \"RESTABLECER\".")
}
function validar_email(){
    var expresion = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;;

    if (expresion.test(document.getElementById("email").value)){
        document.getElementById("error_email").style.display = 'none';
        return true;
    }
    else{
        document.getElementById("error_email").style.display = 'contents';
        return false;
    }
}

function validar_pass(){
    var pass = document.getElementById("pass").value;

    if (pass.length < 6){
        document.getElementById("error_pass").style.display = 'contents';
        return false;
    }
    else{
        document.getElementById("error_pass").style.display = 'none';
        return true;
    }
}

function puedo_pulsar_login(){
    if (validar_email()){
        document.getElementById("boton").disabled = false;
    }
    else{
        document.getElementById("boton").disabled = true;
    }
}

function puedo_pulsar_registro(){
    var email_valido = validar_email();
    var pass_valida = validar_pass();
    if (email_valido && pass_valida){
        document.getElementById("boton").disabled = false;
    }
    else{
        document.getElementById("boton").disabled = true;
    }
}

function completarCamposUsuario(nombreUsuario, idUsuario, rolUsuario){
    document.getElementById('nombreAEliminar').innerHTML = "<h4>" + nombreUsuario + "<h4>";
    document.getElementById('usuarioAEliminar').innerHTML = "<h4>" + idUsuario + "<h4>";
    document.getElementById('usernameAEliminar').setAttribute('value',idUsuario);
    document.getElementById('rolARedirigir').setAttribute('value',rolUsuario);
}

function completarCamposGrupo(nombreGrupo, facilitadorGrupo){
    document.getElementById('nombreGrupoAEliminar').innerHTML = nombreGrupo;
    document.getElementById('nombreGrupo').setAttribute('value',nombreGrupo);
    document.getElementById('facilitadorACargo').setAttribute('value',facilitadorGrupo);
}

function completarCamposTarea(nombreTarea){
    document.getElementById('nombreAEliminar').innerHTML = nombreTarea;
    document.getElementById('tareaAEliminar').setAttribute('value',nombreTarea);
}

function completarCamposTareaCreador(nombreTarea,nombreCreador){
    document.getElementById('nombreAEliminar').innerText = nombreTarea + " (" + nombreCreador + ")";
    document.getElementById('tareaAEliminar').setAttribute('value',nombreTarea);
    document.getElementById('facilitador').setAttribute('value',nombreCreador);
}

function completarCamposAsignacion(creador, socioAsignado, nombreTarea, fechaEntrega){
    document.getElementById('nombreAEliminar').innerText = nombreTarea;
    document.getElementById('creador').setAttribute('value',creador);
    document.getElementById('socioAsignado').setAttribute('value',socioAsignado);
    document.getElementById('nombreTarea').setAttribute('value',nombreTarea);
    document.getElementById('fechaEntrega').setAttribute('value',fechaEntrega);
}
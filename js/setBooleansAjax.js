function notificarNuevoMensaje(){
    var info = {
        nombreTarea: document.getElementById('nombreTareaEliminar').value,
        fechaEntrega : document.getElementById('fechaEntrega').value,
        creador: document.getElementById('creador').value,
        socioAsignado: document.getElementById('socioAsignado').value
    };

    if(info.length != 0){
        $.ajax({
            data:{info},
            url:'notificarNuevoMensaje.php',
            type:'post',
            beforeSend:function(){console.log("Enviando...")}
        });
    }
}
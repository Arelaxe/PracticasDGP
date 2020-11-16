<?php
    require_once "../operaciones_api/autenticacionApi.php";
    require_once "../guardarImagen.php";
    session_start();

    if(isset($_SESSION['usuario']) && isset($_POST['nombre'])){

        /*Extraemos la info original de la tarea */
        $infoTarea = array();
        $infoTarea['nombre'] = $_POST['nombre'];
        $infoTarea['creador'] = $_SESSION['usuario'];
        $jsonInfoTarea = json_encode($infoTarea);
        $resultado = json_decode(infoTareaApi($jsonInfoTarea));
        var_dump($resultado);

        /*Extraemos del formulario los datos para su edición*/
        $infoTareaPeticion = array();
        $infoTareaPeticion['nombre'] = $_POST['nombre'];
        $infoTareaPeticion['creador'] = $_SESSION['usuario'];
        $infoTareaPeticion['descripcion'] = $_POST['descripcion'];

        $fotoTarea = saveFile("fotoTarea");
            
        if ($fotoTarea != ""){
            $infoTareaPeticion['fotoTarea'] = $fotoTarea;
            sendFileApi($fotoTarea);
        }
        else{
            $infoTareaPeticion['fotoTarea'] = $resultado[0]->fotoTarea;
        }

        $videoTarea = saveFile("videoTarea");
        
        if ($videoTarea != null){
            $infoTareaPeticion['videoTarea'] = $videoTarea;
            sendFileApi($videoTarea);
        }
        else{
            $infoTareaPeticion['videoTarea'] = $resultado[0]->videoTarea;
        }

        $audioTarea = saveFile("audioTarea");
        
        if ($audioTarea != null){
            $infoTareaPeticion['audioTarea'] = $audioTarea;
            sendFileApi($audioTarea);
        }
        else{
            $infoTareaPeticion['audioTarea'] = $resultado[0]->audioTarea;
        }


        $jsonPeticion = json_encode($infoTareaPeticion);
        $result = editarTareaApi($jsonPeticion);

        header('Location: ../tarea.php?nombre='.$infoTareaPeticion['nombre']);
    }
    else {
        print ("Nope");
    }
?>
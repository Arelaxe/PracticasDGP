<?php
    require_once "../operaciones_api/autenticacionApi.php";
    require_once "../guardarImagen.php";
    session_start();

    if(isset($_SESSION['usuario']) && isset($_POST['nombre'])){
        $infoTareaPeticion = array();
        $infoTareaPeticion['nombre'] = $_POST['nombre'];
        $infoTareaPeticion['creador'] = $_SESSION['usuario'];
        $infoTareaPeticion['descripcion'] = $_POST['descripcion'];

        $fotoTarea = saveFile("fotoTarea");
            
        if ($fotoTarea != null){
            $infoTareaPeticion['fotoTarea'] = $fotoTarea;
            sendFileApi($fotoTarea);
        }
        else{
            $infoTareaPeticion['fotoTarea'] = "";
        }

        $videoTarea = saveFile("videoTarea");
        
        if ($videoTarea != null){
            $infoTareaPeticion['videoTarea'] = $videoTarea;
            sendFileApi($videoTarea);
        }
        else{
            $infoTareaPeticion['videoTarea'] = "";
        }

        $audioTarea = saveFile("audioTarea");
        
        if ($audioTarea != null){
            $infoTareaPeticion['audioTarea'] = $audioTarea;
            sendFileApi($audioTarea);
        }
        else{
            $infoTareaPeticion['audioTarea'] = "";
        }


        $jsonPeticion = json_encode($infoTareaPeticion);
        $result = editarTareaApi($jsonPeticion);

        header('Location: ../tarea.php?nombre='.$infoTareaPeticion['nombre']);
    }
    else {
        print ("Nope");
    }
?>
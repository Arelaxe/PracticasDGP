<?php
    include_once("../operaciones_api/autenticacionApi.php");
    require_once "../guardarImagen.php";

    session_start();
    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "admin" || $_SESSION['rol'] == "ambos")){
     if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoTarea = array();

        $infoTarea['nombre'] = $_POST['nombreTarea'];
        $infoTarea['descripcion'] = $_POST['descripcionTarea'];
        $infoTarea['creador'] = $_SESSION['usuario'];
        $infoTarea['fechaCreacion'] = date("d/m/y)");

        if (isset($_FILES['fotoTarea'])){
            $foto = saveFile("fotoTarea");
            if ($foto != null){
                $infoTarea['fotoTarea'] = $foto;
                sendFileApi($foto);
            }
        }
        
        if ($_FILES['videoTarea'] != ""){
            $video = saveFile("videoTarea");
            if ($video != null){
                $infoTarea['videoTarea'] = $video;
                sendFileApi($video);
            }
        }
        
        if ($_FILES['audioTarea'] != null){
            $audio = saveFile("audioTarea");
            if ($audio != null){
                $infoTarea['audioTarea'] = $audio;
                sendFileApi($audio);
		    }
        }

        var_dump($infoTarea);

        $jsoninfoTarea = json_encode($infoTarea);

        $result = crearTareaApi($jsoninfoTarea);
        $result = json_decode($result);

            if ($result->name == "MongoError" && $result->code == 11000){
                header("Location: ../crear_tarea.php?error=11000");  
		    }
            else{
                header('Location: ../tarea.php?nombre='.$infoTarea['nombre']);
		    }
     }
    }
?>
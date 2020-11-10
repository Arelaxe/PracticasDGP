<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "facillitador" || $_SESSION['rol'] == "ambos")){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $infoGrupo = array();

            $infoGrupo['nombre'] = $_POST['nombre'];
            $infoGrupo['descripcion'] = $_POST['descripcion'];
            $infoGrupo['facilitadorACargo'] = $_SESSION['usuario'];
            $infoGrupo['socios'] = array();
            $jsoninfoGrupo = json_encode($infoGrupo);

            $result = crearGrupoApi($jsoninfoGrupo);
            var_dump($result);
            $result = json_encode($result);
            $result = json_decode($result);

            if ($result->name == "MongoError" && $result->code == 11000){
                header("Location: ../crear_grupo.php?error=11000");  
		    }
            else{
                header("Location: ../mis_grupos.php");
                var_dump($result);
		    }
        }
    }

?>
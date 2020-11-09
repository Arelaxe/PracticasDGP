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
        }
    }

?>
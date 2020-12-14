<?php
    require_once "../operaciones_api/autenticacionApi.php";
    session_start();
    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $desvinculacion = array();
            $desvinculacion['user_facilitador'] = $_SESSION['usuario'];
            $desvinculacion['user_socio'] = $_POST['socioAVincular'];
            $jsonDesvinculacion = json_encode($desvinculacion);

            $desvinculacionMD = array();
            $desvinculacionMD['facilitador'] = $_SESSION['usuario'];
            $desvinculacionMD['socio'] = $_POST['socioAVincular'];
            $jsonDesvinculacionMD = json_encode($desvinculacionMD);

            eliminarMDApi($jsonDesvinculacionMD);
            desvincularSocioApi($jsonDesvinculacion);

            header('Location: ../mis_socios.php');
        }
    }
?>
<?php
    require_once "../operaciones_api/autenticacionApi.php";
    session_start();
    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $desvinculacion = array();
            $desvinculacion['user_facilitador'] = $_SESSION['usuario'];
            $desvinculacion['user_socio'] = $_POST['socioAVincular'];
            $jsonDesvinculacion = json_encode($desvinculacion);
            desvincularSocioApi($jsonVinculacion);

            header('Location: ../mis_socios.php');
        }
    }
?>
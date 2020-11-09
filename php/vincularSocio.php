<?php
    require_once "../operaciones_api/autenticacionApi.php";
    session_start();
    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $vinculacion = array();
            $vinculacion['user_facilitador'] = $_SESSION['usuario'];
            $vinculacion['user_socio'] = $_POST['socioAVincular'];
            $jsonVinculacion = json_encode($vinculacion);
            vincularSocioApi($jsonVinculacion);

            header('Location: ../mis_socios.php');
        }
    }
?>
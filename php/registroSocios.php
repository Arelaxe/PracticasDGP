<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "admin" || $_SESSION['rol'] == "ambos")){
     if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoRegistro = array();

        $infoRegistro['username'] = $_POST['Identificador'];
        $infoRegistro['password'] = $_POST['Contrasena'];

        $jsoninfoRegistro = json_encode($infoRegistro);

        var_dump($jsoninfoRegistro);
    }
    }
?>
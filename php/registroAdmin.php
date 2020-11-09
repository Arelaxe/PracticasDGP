<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "admin" || $_SESSION['rol'] == "ambos")){
     if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoRegistro = array();
        $sociosACargo = array();

        $infoRegistro['nombre'] = $_POST['nombre'];
        $infoRegistro['username'] = $_POST['email'];
        $infoRegistro['password'] = md5($_POST['pass']);
        $infoRegistro['rol'] = $_POST['rol'];
        $infoRegistro['direccion'] = $_POST['direccion'];
        $infoRegistro['telefono'] = $_POST['telefono'];
        $infoRegistro['sociosACargo'] = $sociosACargo;

        $jsoninfoRegistro = json_encode($infoRegistro);

        $result = registroApi($jsoninfoRegistro);
        $result = json_decode($result);

        if ($result->name == "MongoError" && $result->code == 11000){
            header("Location: ../registro.php?error=11000");  
		}
        else{
            header("Location: ../perfil.php?username=".$infoRegistro['username']);  
		}
    }
    }

?>
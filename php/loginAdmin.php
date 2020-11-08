<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if(isset($_SESSION['usuario'])){
        header("Location: ../pagina_inicio.php");
    }
    else if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoLogin = array();

        $infoLogin['username'] = $_POST['email'];
        $infoLogin['passwd'] = md5($_POST['pass']);

        $jsonInfoLogin = json_encode($infoLogin);

        $result = inicioSesionApi($jsonInfoLogin);
        

        if ($result === "facilitador" || $result === "admin" || $result === "ambos"){
            $_SESSION['usuario'] = $infoLogin['username'];
            $_SESSION['rol'] = $result; 
            header("Location: ../pagina_inicio.php");
        }
        else{
            header("Location: ../login.php");
        }
    }   

?>
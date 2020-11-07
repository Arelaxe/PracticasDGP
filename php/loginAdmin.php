<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if(isset($_SESSION['usuario'])){
        print ("Hola usuario, ya estabas logeado pero te queremos igual");
    }
    else if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoLogin = array();

        $infoLogin['email'] = $_POST['email'];
        $infoLogin['passwd'] = md5($_POST['pass']);

        $jsonInfoLogin = json_encode($infoLogin);

        $result = inicioSesionApi($jsonInfoLogin);
        

        if ($result === "facilitador" || $result === "admin" || $result === "ambos"){
            $_SESSION['usuario'] = $infoLogin['email'];
            $_SESSION['rol'] = $result; 
            header("Location: ../pagina_inicio.php");
        }
    }   

?>
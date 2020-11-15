<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "admin" || $_SESSION['rol'] == "ambos")){
     if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoRegistro = array();
        $facilitadoresACargo = array();
        $grupos = array();

        $infoRegistro['nombre'] = $_POST['Nombre'];
        $infoRegistro['username'] = $_POST['Identificador'];
        $infoRegistro['password'] = md5(utf8_encode($_POST['Contrasena']));
        $infoRegistro['rol'] = "socio";
        $infoRegistro['preferenciaTexto'] = true;
        $infoRegistro['preferenciaAudio'] = true;
        $infoRegistro['preferenciaVideo'] = true;
        $infoRegistro['facilitadoresACargo'] = $facilitadoresACargo;
        $infoRegistro['grupos'] = $grupos;
        $infoRegistro['imagenPerfil'] = 'default.jpg';

        $jsoninfoRegistro = json_encode($infoRegistro);

        $result = registroApi($jsoninfoRegistro);
            $result = json_decode($result);

            if ($result->name == "MongoError" && $result->code == 11000){
                header("Location: ../registrosocios.php?error=11000");  
		    }
            else{
                header('Location: ../perfil.php?username='.$infoRegistro['username']);
		    }
     }
    }
?>
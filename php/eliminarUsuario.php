<?php
    include_once("../operaciones_api/autenticacionApi.php");

    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $rol = $_POST['rol'];

        $infoEliminar = array();
        $infoEliminar['username'] = $_POST['username'];

        $jsonEliminacion = json_encode($infoEliminar);
        
        eliminarUsuarioApi($jsonEliminacion);
<<<<<<< HEAD

        if ($rol == "admin" or $rol == "ambos") {
            header('Location: ../listado_administradores.php');
        } else if ($rol == "facilitador") {
            header('Location: ../listado_facilitadores.php');
        } else if ($rol == "socio"){
            header('Location: ../listado_socios.php');
        }
               
=======
>>>>>>> 5c35205dcd5a6eaebb14ede6bc86667a1524cf0f
    }
?>
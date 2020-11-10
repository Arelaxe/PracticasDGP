<?php
    include_once("../operaciones_api/autenticacionApi.php");

    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoEliminar = array();

        $infoEliminar['nombreGrupo'] = $_POST['nombreGrupo'];
        $infoEliminar['username'] = $_POST['facilitadorACargo'];

        $jsonEliminacion = json_encode($infoEliminar);
        
        $result = eliminarGrupoApi($jsonEliminacion);

       header('Location: ../mis_grupos.php');           
    }
?>
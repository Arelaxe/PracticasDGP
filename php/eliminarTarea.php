<?php
    include_once("../operaciones_api/autenticacionApi.php");

    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $rol = $_POST['rol'];

        $infoEliminar = array();
        $infoEliminar['nombreTarea'] = $_POST['tareaAEliminar'];
        $infoEliminar['username'] = $_POST['facilitador'];

        $jsonEliminacion = json_encode($infoEliminar);

        eliminarTareaApi($jsonEliminacion);

        header('Location: ../listado_tareas.php');
    }
?>
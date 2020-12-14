<?php
    include_once("../operaciones_api/autenticacionApi.php");

    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $rol = $_POST['rol'];

        $infoEliminar = array();
        $infoEliminar['nombreTarea'] = $_POST['tareaAEliminar'];
        $infoEliminar['username'] = $_POST['facilitador'];

        $jsonEliminacion = json_encode($infoEliminar);

        eliminarTareaApi($jsonEliminacion);

        echo("<script>console.log(document.referrer);if(document.referrer.includes('tarea.php')) window.location.replace('../mis_tareas.php') ; else window.history.go(-1) ;</script>");
    }
?>
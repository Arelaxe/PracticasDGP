<?php
    include_once("../operaciones_api/autenticacionApi.php");

    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoEliminar = array();
        $infoEliminar['creador'] = $_POST['creador'];
        $infoEliminar['socioAsignado'] = $_POST['socioAsignado'];
        $infoEliminar['nombreTarea'] = $_POST['nombreTarea'];
        $infoEliminar['fechaEntrega'] = $_POST['fechaEntrega'];
        $jsonEliminacion = json_encode($infoEliminar);

        eliminarTareaEnviadaApi($jsonEliminacion);

        header('Location: ../listado_asignacion.php');
    }
?>
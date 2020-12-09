<?php
    require_once "operaciones_api/autenticacionApi.php";
    var_dump($_POST);

    $infoTareaNotificada = array();
    $infoTareaNotificada = $_POST['info'];
    $jsonInfoTareaNotificada = json_encode($infoTareaNotificada);

    $result = nuevoMensajeWebApi($jsonInfoTareaNotificada);
?>
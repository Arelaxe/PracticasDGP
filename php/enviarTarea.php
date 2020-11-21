<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();

    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $infoTareaEnviar = array();
            $infoTareaEnviar['nombreTarea'] = $_POST['nombre'];
            $infoTareaEnviar['creador'] = $_POST['creador'] ;
            $_POST['permiteVideo'] === "on" ? $infoTareaEnviar['permiteVideo'] = true : $infoTareaEnviar['permiteVideo'] = false;
            $_POST['permiteAudio'] === "on" ? $infoTareaEnviar['permiteAudio'] = true : $infoTareaEnviar['permiteAudio'] = false;
            $_POST['permiteTexto'] === "on" ? $infoTareaEnviar['permiteTexto'] = true : $infoTareaEnviar['permiteTexto'] = false;
            $infoTareaEnviar['socioAsignado'] = $_POST['socioAsignado'];
            $timestamp = new DateTime();
            $infoTareaEnviar['fechaEntrega'] = $timestamp->format(DateTimeInterface::W3C);
            $timestampFinal = date_create($_POST['fechaLimiteEntrega']);
            $infoTareaEnviar['fechaLimiteEntrega'] = $timestampFinal->format(DateTimeInterface::W3C);
            $jsonInfoTareaEnviar = json_encode($infoTareaEnviar);

            $result = enviarTareaApi($jsonInfoTareaEnviar);
            header('Location: ../tarea.php?nombre=' . $infoTareaEnviar['nombreTarea']);
        }
    }
?>
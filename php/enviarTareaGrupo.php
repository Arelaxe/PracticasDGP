<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();

    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $infoTareaEnviar = array();
            $idGrupo = array();
            $idGrupo['nombreGrupo'] = $_POST['grupoAsignado'];
            $idGrupo['facilitadorACargo'] = $_SESSION['usuario'];
            $jsonIdGrupo = json_encode($idGrupo);
            $infoGrupo = infoGrupoApi($jsonIdGrupo);

            $infoTareaEnviar['nombreTarea'] = $_POST['nombre'];
            $infoTareaEnviar['creador'] = $_POST['creador'] ;
            $_POST['permiteVideo'] === "on" ? $infoTareaEnviar['permiteVideo'] = true : $infoTareaEnviar['permiteVideo'] = false;
            $_POST['permiteAudio'] === "on" ? $infoTareaEnviar['permiteAudio'] = true : $infoTareaEnviar['permiteAudio'] = false;
            $_POST['permiteTexto'] === "on" ? $infoTareaEnviar['permiteTexto'] = true : $infoTareaEnviar['permiteTexto'] = false;
            $timestamp = new DateTime();
            $infoTareaEnviar['fechaEntrega'] = $timestamp->format(DateTimeInterface::W3C);
            $timestampFinal = date_create($_POST['fechaLimiteEntrega']);
            $infoTareaEnviar['fechaLimiteEntrega'] = $timestampFinal->format(DateTimeInterface::W3C);
            
            foreach($infoGrupo[0]->socios as $socio){
                $infoTareaEnviar['socioAsignado'] = $socio;
                $jsonInfoTareaEnviar = json_encode($infoTareaEnviar);

                $result = enviarTareaApi($jsonInfoTareaEnviar);
            }
            
            header('Location: ../tarea.php?nombre=' . $infoTareaEnviar['nombreTarea']);
        }
    }
?>
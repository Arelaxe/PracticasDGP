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
            $_POST['formatoEntrega'] === "permiteVideo" ? $infoTareaEnviar['permiteVideo'] = true : $infoTareaEnviar['permiteVideo'] = false;
            $_POST['formatoEntrega'] === "permiteAudio" ? $infoTareaEnviar['permiteAudio'] = true : $infoTareaEnviar['permiteAudio'] = false;
            $_POST['formatoEntrega'] === "permiteTexto" ? $infoTareaEnviar['permiteTexto'] = true : $infoTareaEnviar['permiteTexto'] = false;
            $timestamp = new DateTime();
            $infoTareaEnviar['fechaEntrega'] = $timestamp->format("Y-m-d\TH:i:s");
            $timestampFinal = date_create($_POST['fechaLimiteEntrega']);
            $infoTareaEnviar['fechaLimiteEntrega'] = $timestampFinal->format("Y-m-d\TH:i:s");
            $infoTareaEnviar['respondida'] = false ;
            $infoTareaEnviar['nuevoMensaje'] = false;
            $infoTareaEnviar['nuevoMensajeFacilitador'] = false;
            $infoTareaEnviar['respuesta'] = "";
            $infoTareaEnviar['vista'] = true;

            $infoTareaObjetivo = array();
            $infoTareaObjetivo['nombre'] = $infoTareaEnviar['nombreTarea'] ;
            $infoTareaObjetivo['creador'] = $infoTareaEnviar['creador'] ;
            $jsonInfoTareaObjetivo = json_encode($infoTareaObjetivo);
            $resultadoTarea = json_decode(infoTareaApi($jsonInfoTareaObjetivo));

            foreach($resultadoTarea as $tarea){
                $infoTareaEnviar['tieneAudio'] = !(empty($tarea->audioTarea)) ;
                $infoTareaEnviar['tieneVideo'] = !(empty($tarea->videoTarea)) ;
                $infoTareaEnviar['tieneTexto'] = !(empty($tarea->descripcion)) ;
                if(!($infoTareaEnviar['permiteVideo'] || $infoTareaEnviar['permiteTexto'] || $infoTareaEnviar['permiteAudio'])){
                    header('Location: ../enviar_tarea_socio.php?nombre='.$infoTareaEnviar['nombreTarea'].'&error=440');
                }
                else if(!$infoTareaEnviar['tieneAudio'] && !$infoTareaEnviar['tieneVideo'] && !$infoTareaEnviar['tieneTexto']){
                    header('Location: ../enviar_tarea_grupo.php?nombre='.$infoTareaEnviar['nombreTarea'].'&error=410');
                }
                else{
                    foreach($infoGrupo[0]->socios as $socio){
                        $infoTareaEnviar['socioAsignado'] = $socio;
                        $infoChat = infoChatApi();
                        $infoTareaEnviar['idChat'] = $infoChat->idChat;
                        $infoTareaEnviar['nombreChat'] = $infoChat->nombreChat;
                        $idChat = array();
                        $idChat['idChat'] = $infoChat->idChat;
                        $jsonIdChat = json_encode($idChat);
                        borrarChatApi($jsonIdChat);
                        $jsonInfoTareaEnviar = json_encode($infoTareaEnviar);
        
                        $result = enviarTareaApi($jsonInfoTareaEnviar);
                    }
                    
                    header('Location: ../tarea.php?nombre=' . $infoTareaEnviar['nombreTarea']);
                }
            }
        }
    }
?>

<?php
    include_once("../operaciones_api/autenticacionApi.php");

    session_start();

    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $infoTareaEnviar = array();
            $infoTareaEnviar['nombreTarea'] = $_POST['nombre'];
            $infoTareaEnviar['creador'] = $_POST['creador'] ;
            $_POST['formatoEntrega'] === "permiteVideo" ? $infoTareaEnviar['permiteVideo'] = true : $infoTareaEnviar['permiteVideo'] = false;
            $_POST['formatoEntrega'] === "permiteAudio" ? $infoTareaEnviar['permiteAudio'] = true : $infoTareaEnviar['permiteAudio'] = false;
            $_POST['formatoEntrega'] === "permiteTexto" ? $infoTareaEnviar['permiteTexto'] = true : $infoTareaEnviar['permiteTexto'] = false;
            $infoTareaEnviar['socioAsignado'] = $_POST['socioAsignado'];
            $timestamp = new DateTime();
            $infoTareaEnviar['fechaEntrega'] = $timestamp->format("Y-m-d\TH:i:s");
            $timestampFinal = date_create($_POST['fechaLimiteEntrega']);
            $infoTareaEnviar['fechaLimiteEntrega'] = $timestampFinal->format("Y-m-d\TH:i:s");
            $infoTareaEnviar['respondida'] = false ;
            $infoTareaEnviar['nuevoMensaje'] = false;
            $infoTareaEnviar['nuevoMensajeFacilitador'] = false;
            $infoTareaEnviar['vista'] = true;
            $infoTareaEnviar['respuesta'] = "";
            $infoChat = infoChatApi();
            $infoTareaEnviar['idChat'] = $infoChat->idChat;
            $infoTareaEnviar['nombreChat'] = $infoChat->nombreChat;
            $idChat = array();
            $idChat['idChat'] = $infoChat->idChat;
            $jsonIdChat = json_encode($idChat);
            borrarChatApi($jsonIdChat);

            /*Comprobamos que la tarea que se envia al socio tiene información según sus preferencias*/
            /*Comprobamos que el socio puede enviar una tarea según sus preferencias*/

            $infoSocioObjetivo = array();
            $infoSocioObjetivo['username'] = $infoTareaEnviar['socioAsignado'] ;
            $jsonInfoSocioObjetivo = json_encode($infoSocioObjetivo);
            $resultado = infoPerfilApi($jsonInfoSocioObjetivo);

            $infoTareaObjetivo = array();
            $infoTareaObjetivo['nombre'] = $infoTareaEnviar['nombreTarea'] ;
            $infoTareaObjetivo['creador'] = $infoTareaEnviar['creador'] ;
            $jsonInfoTareaObjetivo = json_encode($infoTareaObjetivo);
            $resultadoTarea = json_decode(infoTareaApi($jsonInfoTareaObjetivo));

            foreach($resultado as $socio){
                var_dump($socio);
                foreach($resultadoTarea as $tarea){
                    var_dump($tarea);
                    var_dump($infoTareaEnviar);
                    $infoTareaEnviar['tieneAudio'] = !(empty($tarea->audioTarea)) ;
                    $infoTareaEnviar['tieneVideo'] = !(empty($tarea->videoTarea)) ;
                    $infoTareaEnviar['tieneTexto'] = !(empty($tarea->descripcion)) ;
                   // var_dump($infoTareaEnviar);
                    if(!($infoTareaEnviar['permiteVideo'] || $infoTareaEnviar['permiteTexto'] || $infoTareaEnviar['permiteAudio'])){
                        header('Location: ../enviar_tarea_socio.php?nombre='.$infoTareaEnviar['nombreTarea'].'&error=440');
                    }

                    else if(!(($infoTareaEnviar['tieneTexto'] && $infoTareaEnviar['tieneTexto'] === $socio->preferenciaTexto)||($infoTareaEnviar['tieneVideo'] && $infoTareaEnviar['tieneVideo'] === $socio->preferenciaVideo)||($infoTareaEnviar['tieneAudio'] && $infoTareaEnviar['tieneAudio'] === $socio->preferenciaAudio))){
                        header('Location: ../enviar_tarea_socio.php?nombre='.$infoTareaEnviar['nombreTarea'].'&error=440');
                    }
                    else{
                        if($socio->preferenciaVideo === $infoTareaEnviar['permiteVideo'] || $socio->preferenciaAudio === $infoTareaEnviar['permiteAudio'] || $socio->preferenciaTexto === $infoTareaEnviar['permiteTexto']){
                            $jsonInfoTareaEnviar = json_encode($infoTareaEnviar);
                            $result = enviarTareaApi($jsonInfoTareaEnviar);
                            header('Location: ../tarea_enviada.php?nombre=' . $infoTareaEnviar['nombreTarea'] . "&socio=" . $infoTareaEnviar['socioAsignado'] . "&fechaEntrega=" . $infoTareaEnviar['fechaEntrega']);
                        }
        
                        else{
                            header('Location: ../enviar_tarea_socio.php?nombre='.$infoTareaEnviar['nombreTarea'].'&error=450');
                        }
                    }
                }

                
            }
        }
    }
?>
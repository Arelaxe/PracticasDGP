<?php
    require_once "../operaciones_api/autenticacionApi.php";
    session_start();
    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $vinculacion = array();
            $vinculacion['user_facilitador'] = $_SESSION['usuario'];
            $vinculacion['user_socio'] = $_POST['socioAVincular'];
            $jsonVinculacion = json_encode($vinculacion);
            vincularSocioApi($jsonVinculacion);

            $chat = array();
            $chat['facilitador'] = $_SESSION['usuario'];
            $chat['socio'] = $_POST['socioAVincular'];
            $infoChat = infoChatApi();
            $chat['idChat'] = $infoChat->idChat;
            $chat['nombreChat'] = $infoChat->nombreChat;
            $jsonChat = json_encode($chat);
            establecerMDApi($jsonChat);

            $idChat = array();
            $idChat['idChat'] = $infoChat->idChat;
            $jsonIdChat = json_encode($idChat);
            borrarChatApi($jsonIdChat);

            header('Location: ../mis_socios.php');
        }
    }
?>
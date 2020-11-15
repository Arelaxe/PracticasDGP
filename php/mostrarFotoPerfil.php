<?php
    require_once 'operaciones_api/autenticacionApi.php';
    include_once("guardarImagen.php");

    function fotoPerfil($username){
        $usuario = array();
        $usuario['username'] = $username ;
        $jsonUsuario = json_encode($usuario);
        $resultadoInfo = infoPerfilApi($jsonUsuario);
        foreach($resultadoInfo as $item){
            getImageApi($item->imagenPerfil);
            $imagen = 'img/' . $item->imagenPerfil;
        }

        return $imagen;
    }
?>
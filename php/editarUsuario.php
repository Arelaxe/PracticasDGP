<?php
    require_once "../operaciones_api/autenticacionApi.php";

    if(isset($_POST['username'])){
        var_dump($_POST);
        $infoUsuario = array();
        $infoUsuario['username'] = $_POST['oldUsername'];
        $infoUsuario = infoPerfilApi(json_encode($infoUsuario));
        var_dump($infoUsuario);

        $infoUsuarioEditado = array();
        $infoUsuarioEditado['oldUsername'] =  $_POST['oldUsername'];
        $infoUsuarioEditado['username'] = $_POST['username'];
        $infoUsuarioEditado['nombre'] = $_POST['nombre'];
        $infoUsuarioEditado['rol'] = $infoUsuario['rol'];

        if($infoUsuarioEditado['rol'] == "admin" || $infoUsuarioEditado['rol'] == "ambos" || $infoUsuarioEditado['rol'] == "facilitador"){
            $infoUsuarioEditado['direccion'] = $_POST['direccion'];
            $infoUsuarioEditado['telefono'] = $_POST['telefono'];

            if(isset($_POST['ContrasenaActual']) && isset($_POST['ContrasenaNueva']) && isset($_POST['ContrasenaConfirmar']) ){
                if(md5($_POST['ContrasenaActual']) == $_POST['password'] && $_POST['ContrasenaNueva'] == $_POST['ContrasenaConfirmar']){
                    $infoUsuarioEditado['password'] = md5($_POST['ContrasenaNueva']);
                }
                else header('Location: ../editar.php?error=406');
            }
            else $infoUsuarioEditado['password'] = $infoUsuario['password'];

            if($infoUsuarioEditado['rol'] == "ambos" || $infoUsuarioEditado['rol'] == "facilitador"){
                $infoUsuarioEditado['sociosACargo'] = $infoUsuario['sociosACargo'];
            }
        }
        else{
            if(isset($_POST['password'])){
                $infoUsuarioEditado['password'] = md5(utf8_encode($_POST['password'])) ;
            }
            $infoUsuarioEditado['facilitadoresACargo'] = $infoUsuario['facilitadoresACargo'];
        }

        var_dump($infoUsuarioEditado);
    }
?>
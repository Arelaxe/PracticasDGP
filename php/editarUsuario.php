<?php
    require_once "../operaciones_api/autenticacionApi.php";
    session_start();

    if(isset($_SESSION['rol']) && isset($_POST['oldUsername'])){
        $infoUsuarioPeticion = array();
        $infoUsuarioPeticion['username'] = $_POST['oldUsername'];
        $jsonInfoUsuario = json_encode($infoUsuarioPeticion);
        $infoUsuario = infoPerfilApi($jsonInfoUsuario);

        foreach($infoUsuario as $usuario){
            $infoUsuarioEditado = array();
            $infoUsuarioEditado['oldUsername'] =  $_POST['oldUsername'];
            $infoUsuarioEditado['username'] = $_POST['username'];
            $infoUsuarioEditado['nombre'] = $_POST['nombre'];
            $infoUsuarioEditado['rol'] = $usuario->rol;

            if($infoUsuarioEditado['rol'] == "admin" || $infoUsuarioEditado['rol'] == "ambos" || $infoUsuarioEditado['rol'] == "facilitador"){
                $infoUsuarioEditado['direccion'] = $_POST['direccion'];
                $infoUsuarioEditado['telefono'] = $_POST['telefono'];
                $infoUsuarioEditado['sociosACargo'] = $usuario->sociosACargo;

                if(!empty($_POST['ContrasenaNueva']) && !empty($_POST['ContrasenaConfirmar']) ){
                    if((md5($_POST['ContrasenaActual']) === $usuario->password  && $_POST['ContrasenaNueva'] === $_POST['ContrasenaConfirmar']) || (($_POST['ContrasenaNueva'] === $_POST['ContrasenaConfirmar']) && ($_SESSION['rol'] == "admin" || $_SESSION['rol'] == "ambos"))){
                        print(md5($_POST['ContrasenaNueva']));
                        $infoUsuarioEditado['password'] = md5($_POST['ContrasenaNueva']);
                        $jsonInfoUsuarioEditado = json_encode($infoUsuarioEditado);
                        $result = editarUsuarioApi($jsonInfoUsuarioEditado);
                        ($infoUsuarioEditado['rol'] == "admin" || $infoUsuarioEditado['rol'] == "ambos") ? header('Location: ../listado_administradores.php') : header('Location: ../listado_facilitadores.php');

                    }
                    else{
                        if($_POST['ContrasenaNueva'] != $_POST['ContrasenaConfirmar']) {
                            header('Location: ../editar.php?username='. $usuario->username .'&error=406');
                        }
                        else if(md5($_POST['ContrasenaActual']) != $usuario->password) {
                            header('Location: ../editar.php?username='. $usuario->username .'&error=407');
                        }
                    } 
                }
                else{
                    $infoUsuarioEditado['password'] = $usuario->password;
                    $jsonInfoUsuarioEditado = json_encode($infoUsuarioEditado);
                    $result = editarUsuarioApi($jsonInfoUsuarioEditado);
                    ($infoUsuarioEditado['rol'] == "admin" || $infoUsuarioEditado['rol'] == "ambos") ? header('Location: ../listado_administradores.php') : header('Location: ../listado_facilitadores.php');
                } 
            }
            else{
                if(!empty($_POST['secuencia'])){
                    $infoUsuarioEditado['password'] = md5(utf8_encode($_POST['secuencia'])) ;
                }
                else $infoUsuarioEditado['password'] = $usuario->password ;
                $infoUsuarioEditado['username'] = $_POST['oldUsername'];
                $infoUsuarioEditado['facilitadoresACargo'] = $usuario->facilitadoresACargo;
                $infoUsuarioEditado['preferenciaVideo'] = ($_POST['preferenciaVideo'] === "on") ;
                $infoUsuarioEditado['preferenciaAudio'] = ($_POST['preferenciaAudio'] === "on") ;
                $infoUsuarioEditado['preferenciaTexto'] = ($_POST['preferenciaTexto'] === "on") ;

                $jsonInfoUsuarioEditado = json_encode($infoUsuarioEditado);
                $result = editarUsuarioApi($jsonInfoUsuarioEditado);
                var_dump($jsonInfoUsuarioEditado);
                header('Location: ../listado_socios.php');
            }
        }
    }
?>
<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    require_once "guardarImagen.php";
    require_once 'php/mostrarFotoPerfil.php';

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();
    $error = false;
    $coderror = 0;

    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "GET"){
            if (isset($_GET['error']))
                if ($_GET['error']=="406" or $_GET['error']=="407"){
                    $error = true;
                    $coderror = $_GET['error'] ;
                }
        }
    }

    if(isset($_SESSION['usuario'])) $rol = $_SESSION['rol'];
    else $rol = "";

    if (isset($_GET['username'])) {
        $idUsuario = $_GET['username'];
        //$_SESSION['username'] = $idUsuario;

        $infoPerfil = array();
        $infoPerfil['username'] = $idUsuario;

        $jsonInfoPerfil = json_encode($infoPerfil);

        $result = infoPerfilApi($jsonInfoPerfil);

        $infoUsuario = array();

        foreach($result as $item){
            $infoUsuario['nombre'] = $item->nombre;
            $infoUsuario['username'] = $item->username;
            $infoUsuario['rol'] = $item->rol;
            getImage($item->imagenPerfil);
            $infoUsuario['imagenPerfil'] = "img/".$item->imagenPerfil;

            if($infoUsuario['rol'] == "admin" || $infoUsuario['rol'] == "ambos" || $infoUsuario['rol'] == "facilitador"){
                $infoUsuario['direccion'] = $item->direccion;
                $infoUsuario['telefono'] = $item->telefono;
            }
            else{
                $infoUsuario['preferenciaAudio'] = isset($item->preferenciaAudio) ? $item->preferenciaAudio : false;
                $infoUsuario['preferenciaVideo'] = isset($item->preferenciaVideo) ? $item->preferenciaVideo : false;
                $infoUsuario['preferenciaTexto'] = isset($item->preferenciaTexto) ? $item->preferenciaTexto : false;
            }

            if($infoUsuario['rol'] == "admin" || $infoUsuario['rol'] == "ambos") $infoUsuario['grupo'] = "administradores";
            else if ($infoUsuario['rol'] == "facilitador") $infoUsuario['grupo'] = "facilitadores";
            else $infoUsuario['grupo'] = "socios";
    }
    } else{
        $idUsuario = -1;
    }

    

    echo $twig->render('editar.html', ['id' => $idUsuario, 'infoUsuario' => $infoUsuario, 'rol' => $rol, 'error' => $error, 'coderror' => $coderror, 'img' => fotoPerfil($_SESSION['usuario']), 'cuenta' => $_SESSION['usuario']]);
?>
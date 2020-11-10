<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

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
            if($infoUsuario['rol'] == "admin" || $infoUsuario['rol'] == "ambos" || $infoUsuario['rol'] == "facilitador"){
                $infoUsuario['direccion'] = $item->direccion;
                $infoUsuario['telefono'] = $item->telefono;
            }
            else{

            }

            if($infoUsuario['rol'] == "admin" || $infoUsuario['rol'] == "ambos") $infoUsuario['grupo'] = "administradores";
            else if ($infoUsuario['rol'] == "facilitador") $infoUsuario['grupo'] = "facilitadores";
            else $infoUsuario['grupo'] = "socios";
    }
    } else{
        $idUsuario = -1;
    }

    

    echo $twig->render('editar.html', ['id' => $idUsuario, 'infoUsuario' => $infoUsuario, 'rol' => $rol]);
?>
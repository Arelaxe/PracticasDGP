<?php
    require_once '../vendor/autoload.php';
    require_once 'operaciones_api/autenticacionApi.php';
    include_once("guardarImagen.php");

    session_start();

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $usuario = array();
        $usuario['username'] = $_SESSION['usuario'] ;
        $jsonUsuario = json_encode($usuario);
        $resultadoInfo = infoPerfilApi($jsonUsuario);
        foreach($resultadoInfo as $item){
            getImageApi($item->imagenPerfil);
            $imagen = 'img/' . $item->imagenPerfil;
        }
    }
    else $rol = "";

    echo $twig->render('menuprincipal.html', ['rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => $imagen]);
?>
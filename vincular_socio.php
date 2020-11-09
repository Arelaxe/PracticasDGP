<?php
    require_once 'php/vinculaciones.php';
    require_once('operaciones_api/autenticacionApi.php');
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $infoUsuario['username'] = $_SESSION['usuario'];
        $jsonInfoUsuario = json_encode($infoUsuario);
        $listadoSociosNoVinculados = listadoSociosNoVinculados($jsonInfoUsuario);
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('vincularsocio.html',['rol' => $rol]);
?>
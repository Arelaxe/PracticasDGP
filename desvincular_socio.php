<?php
    require_once 'php/vinculaciones.php';
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $jsonInfoUsuario = json_encode($infoUsuario);
        $listadoSociosVinculados = listadoSociosVinculados($jsonInfoUsuario);
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('desvincularsocio.html',['rol' => $rol]);
?>
<?php
    require_once 'php/listasVinculaciones.php';
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $infoSociosVinculados = array();
        $infoSociosVinculados['user_facilitador'] = $_SESSION['usuario'] ;
        $jsonInfoSociosVinculados = json_encode($infoSociosVinculados);
        $listadoSociosVinculados = listadoSociosVinculados($jsonInfoSociosVinculados);
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('desvincularsocio.html',['rol' => $rol, 'listaSocios' => $listadoSociosVinculados]);
?>
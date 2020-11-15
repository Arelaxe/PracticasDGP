<?php
    require_once 'php/listasVinculaciones.php';
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';

    session_start();
    if(isset($_SESSION['usuario']) && isset($_GET['socioADesvincular'])){
        $rol = $_SESSION['rol'];
        $infoSocio = array();
        $infoSocio['username'] = $_GET['socioADesvincular'];
        $infoSocio['nombre'] = $_GET['nombreADesvincular'];
        $listadoSociosVinculados = array();
        array_push($listadoSociosVinculados,$infoSocio);
    }

    else if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $infoSociosVinculados = array();
        $infoSociosVinculados['user_facilitador'] = $_SESSION['usuario'] ;
        $jsonInfoSociosVinculados = json_encode($infoSociosVinculados);
        $listadoSociosVinculados = listadoSociosVinculados($jsonInfoSociosVinculados);
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('desvincularsocio.html',['rol' => $rol, 'listaSocios' => $listadoSociosVinculados , 'img' => fotoPerfil($_SESSION['usuario']), 'cuenta' => $_SESSION['usuario']]);
?>
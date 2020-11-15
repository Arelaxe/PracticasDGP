<?php
    require_once 'php/listasVinculaciones.php';
    require_once('operaciones_api/autenticacionApi.php');
    require_once 'php/mostrarFotoPerfil.php';
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $infoUsuario['username'] = $_SESSION['usuario'];


        $infoSociosNoVinculados = array();
        $infoSociosNoVinculados['user_facilitador'] = $_SESSION['usuario'] ;
        $jsonInfoSociosNoVinculados = json_encode($infoSociosNoVinculados);
        $listadoSociosNoVinculados = listadoSociosNoVinculados($jsonInfoSociosNoVinculados);
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('vincularsocio.html',['rol' => $rol, 'socios' => $listadoSociosNoVinculados , 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario'])]);
?>
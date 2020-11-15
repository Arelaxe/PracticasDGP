<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';

    session_start();

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $imagen = fotoPerfil($_SESSION['usuario']);
    }
    else $rol = "";

    echo $twig->render('menuprincipal.html', ['rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => $imagen]);
?>
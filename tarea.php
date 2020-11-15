<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('informacionTarea.html',['rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario'])]);
?>
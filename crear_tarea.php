<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';
    

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $cuenta = $_SESSION['usuario'] ;
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('crearTarea.html',['rol' => $rol, 'cuenta' => $cuenta, 'img' => fotoPerfil($_SESSION['usuario']), 'cuenta' => $_SESSION['usuario']]);
?>
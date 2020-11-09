<?php
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('desvincularsocio.html',['rol' => $rol]);
?>
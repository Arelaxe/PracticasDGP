<?php
    require_once '../vendor/autoload.php';
    session_start();
    
    if(isset($_SESSION['usuario'])){
        $loader = new \Twig\Loader\FilesystemLoader('templates');
        $twig = new \Twig\Environment($loader);
        $rol = $_SESSION['rol'];
    
        echo $twig->render('registro.html', ['rol' => $rol]);
    }
?>
<?php
    require_once '../vendor/autoload.php';

    session_start();
    
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);
    
    if(isset($_SESSION['usuario'])){     
        $rol = $_SESSION['rol'];
    }
    else $rol = "";

    echo $twig->render('registrosocios.html', ['rol' => $rol]);
?>
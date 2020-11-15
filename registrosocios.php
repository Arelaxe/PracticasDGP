<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';

    session_start();
    
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);
    
    $error = false;

    if(isset($_SESSION['usuario'])){     
        $rol = $_SESSION['rol'];
        $imagen = fotoPerfil($_SESSION['usuario']);
        if (isset($_GET['error']))
                if ($_GET['error']=="11000")
                    $error = true;
    }
    else $rol = "";

    echo $twig->render('registrosocios.html', ['rol' => $rol, 'error' => $error, 'img' => $imagen, 'cuenta' => $_SESSION['usuario']]);
?>
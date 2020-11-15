<?php
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $cuenta = $_SESSION['usuario'] ;

        if(isset($_GET['nombreTarea'])){
            /*Enviar la info de tarea*/
        }
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('editarTarea.html',['rol' => $rol, 'cuenta' => $cuenta]);
?>
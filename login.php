<?php
    require_once '../vendor/autoload.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        header("Location: ../pagina_inicio.php");
    }

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('login.html');
?>
<?php
    require_once '../vendor/autoload.php';
    session_start();
    
    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "facilitador" || $_SESSION['rol'] == "ambos")){
        $loader = new \Twig\Loader\FilesystemLoader('templates');
        $twig = new \Twig\Environment($loader);
        $rol = $_SESSION['rol'];
        echo $twig->render('creargrupo.html', ['rol' => $rol]);
    }
?>
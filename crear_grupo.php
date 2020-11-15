<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';
    session_start();
    $error = false;

    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "facilitador" || $_SESSION['rol'] == "ambos")){
         if($_SERVER['REQUEST_METHOD'] === "GET"){
            if (isset($_GET['error']))
                if ($_GET['error']=="11000")
                    $error = true;
        }

        $loader = new \Twig\Loader\FilesystemLoader('templates');
        $twig = new \Twig\Environment($loader);
        $rol = $_SESSION['rol'];
        echo $twig->render('creargrupo.html', ['rol' => $rol, 'error' => $error, 'img' => fotoPerfil($_SESSION['usuario']), 'cuenta' => $_SESSION['usuario'] ]);
    }
?>
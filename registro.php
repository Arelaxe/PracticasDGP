<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';
    session_start();
    
    $error = false;
    $imagen = fotoPerfil($_SESSION['usuario']);

    if(isset($_SESSION['usuario']) && ($_SESSION['rol'] == "admin" || $_SESSION['rol'] == "ambos")){
        if($_SERVER['REQUEST_METHOD'] === "GET"){
            if (isset($_GET['error']))
                if ($_GET['error']=="11000")
                    $error = true;
        }
    }

    if(isset($_SESSION['usuario'])){
        $loader = new \Twig\Loader\FilesystemLoader('templates');
        $twig = new \Twig\Environment($loader);
        $rol = $_SESSION['rol'];
    
        echo $twig->render('registro.html', ['rol' => $rol, 'error' => $error, 'cuenta' => $_SESSION['usuario'], 'img' => $imagen]);
    }
    else{
        print("Has de iniciar sesi�n");
	}
?>
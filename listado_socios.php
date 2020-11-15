<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    require_once 'php/mostrarFotoPerfil.php';
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        $imagen = fotoPerfil($_SESSION['usuario']);
        $listado = listadoSociosApi();
    }

    $listado_nombres = array();

    foreach ($listado as $usuario){
        array_push($listado_nombres, $usuario->nombre);
	}

    echo $twig->render('listadosocios.html', ['nombres' => $listado, 'rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => $imagen]);
?>
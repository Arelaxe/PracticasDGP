<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    require_once 'php/mostrarFotoPerfil.php';
    
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        $idFacilitador = array();
        $idFacilitador['creador'] = $_SESSION['usuario'];
        $jsonIdFacilitador = json_encode($idFacilitador);
        $listado = tareasEnviadasApi($jsonIdFacilitador);
        $rol = $_SESSION['rol'];
    }

    echo $twig->render('listadoasignacion.html', ['tareas' => $listado, 'rol' => $rol, 'usuario' => $_SESSION['usuario'], 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario'])]);
?>
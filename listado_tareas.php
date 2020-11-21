<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    require_once 'php/mostrarFotoPerfil.php';
    
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        $listado = listadoTareasAdminApi();
        $rol = $_SESSION['rol'];
    }

    $listado_nombres = array();
    $listado_creadores = array();
    $listado_ids = array();

    foreach ($listado as $tarea){
        array_push($listado_nombres, $tarea->nombre);
        array_push($listado_creadores, $tarea->creador);
        array_push($listado_ids, $tarea->_id);
    }

    echo $twig->render('listadotareas.html', ['nombres' => $listado_nombres, 'creadores' => $listado_creadores , 'ids' => $listado_ids, 'rol' => $rol, 'usuario' => $_SESSION['usuario'], 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario'])]);
?>
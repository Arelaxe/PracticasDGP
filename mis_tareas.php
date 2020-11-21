<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    require_once 'php/mostrarFotoPerfil.php';
    
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        $infoFacilitador = array();
        $infoFacilitador['username'] = $_SESSION['usuario'];
        $jsoninfoFacilitador = json_encode($infoFacilitador);
        $listado = listadoTareasApi($jsoninfoFacilitador);
        $rol = $_SESSION['rol'];
    }

    $listado_nombres = array();
    $listado_ids = array();

    foreach ($listado as $tarea){
        array_push($listado_nombres, $tarea->nombre);
        array_push($listado_ids, $tarea->_id);
	}

    echo $twig->render('mistareas.html', ['nombres' => $listado_nombres, 'ids' => $listado_ids, 'rol' => $rol, 'usuario' => $_SESSION['usuario'], 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario'])]);
?>
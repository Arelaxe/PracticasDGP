<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        $infoFacilitador = array();
        $infoFacilitador['username'] = $_SESSION['usuario'];
        $jsoninfoFacilitador = json_encode($infoFacilitador);
        $listado = listadoTareasApi($jsoninfoFacilitador);
    }

    $listado_nombres = array();

    foreach ($listado as $tarea){
        array_push($listado_nombres, $tarea->nombre);
	}

    echo $twig->render('listadotareas.html', ['nombres' => $listado_nombres]);
?>
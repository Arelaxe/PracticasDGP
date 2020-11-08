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
        $listado = misSociosApi($jsoninfoFacilitador);
    }

    $listado_nombres = array();
    $listado_ids = array();

    foreach ($listado as $socio){
        array_push($listado_nombres, $socio->nombre);
        array_push($listado_ids, $socio->_id);
	}

    echo $twig->render('missocios.html', ['nombres' => $listado_nombres, 'ids' => $listado_ids]);
?>
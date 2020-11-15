<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        
        $listado = listadoFacilitadoresApi();
        $rol = $_SESSION['rol'];
    }

    $listado_nombres = array();

    foreach ($listado as $usuario){
        array_push($listado_nombres, $usuario->nombre);
	}

    echo $twig->render('listadofacilitadores.html', ['nombres' => $listado, 'rol' => $rol]);
?>
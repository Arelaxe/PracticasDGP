<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        
        $listado = listadoSociosApi();
    }

    $listado_nombres = array();

    foreach ($listado as $usuario){
        array_push($listado_nombres, $usuario->nombre);
	}

    echo $twig->render('listadosocios.html', ['nombres' => $listado_nombres]);
?>
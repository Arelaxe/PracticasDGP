<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();
    if ($_SESSION['rol'] == 'admin' || $_SESSION['rol'] == 'ambos'){
        $listado = listadoAdministradoresApi();
        $listado_nombres = array();
        $rol = $_SESSION['rol'];

        foreach($listado as $elemento){
            array_push($listado_nombres, $elemento->nombre);
        }
    }

    echo $twig->render('listadoadministradores.html', ['nombres' => $listado, 'rol' => $rol]);
?>
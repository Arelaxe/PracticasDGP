<?php
    require_once '../vendor/autoload.php';
    require_once "operaciones_api/autenticacionApi.php";

    session_start();

    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
    }
    else $rol = "";

    if (isset($_GET['nombre'])) {
        $nombreTarea = $_GET['nombre'];
        $creador = $_SESSION['usuario'];
        $idFacilitador = array();
        $idFacilitador['username'] = $creador;
        $jsonIdFacilitador = json_encode($idFacilitador);

        $gruposFacilitador = misGruposApi($jsonIdFacilitador);
    }
    else{
        $nombreTarea = -1;
    }

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('enviarTareaGrupo.html', ['nombreTarea' => $nombreTarea, 'creador' => $creador, 'grupos' =>$gruposFacilitador, 'rol' => $rol]);
?>
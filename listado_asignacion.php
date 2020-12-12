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
        $listado = notificacionesTareasApi($jsonIdFacilitador);
        $listadoSocios = array();
        foreach ($listado as $asignacion){
            $infoSocio = array();
            $infoSocio['username'] = $asignacion->socioAsignado ;
            $jsonInfoSocio = json_encode($infoSocio) ;
            $result = infoPerfilApi($jsonInfoSocio);

            foreach ($result as $socio){
                array_push($listadoSocios,$socio->nombre);
            }
        }
        $rol = $_SESSION['rol'];
    }

    echo $twig->render('listadoasignacion.html', ['tareas' => $listado, 'rol' => $rol, 'usuario' => $_SESSION['usuario'], 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario']), 'nombres' => $listadoSocios ]);
?>
<?php
    require_once '../vendor/autoload.php';
    require_once "operaciones_api/autenticacionApi.php";
    require_once 'php/mostrarFotoPerfil.php';

    session_start();

    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
    }
    else $rol = "";

    $error = isset($_GET['error']);
    $codigo = 0 ;
    if(isset($_GET['error'])) $codigo = $_GET['error'];

    if (isset($_GET['nombre'])) {
        $nombreTarea = $_GET['nombre'];
        $creador = $_SESSION['usuario'];
        $idFacilitador = array();
        $idFacilitador['username'] = $creador;
        $jsonIdFacilitador = json_encode($idFacilitador);

        $infoFacilitador = infoPerfilApi($jsonIdFacilitador);

        $socios = array();

        foreach ($infoFacilitador[0]->sociosACargo as $socio){
            $idSocio = array();
            $idSocio['username'] = $socio;
            $jsonIdSocio = json_encode($idSocio);

            $infoSocio = infoPerfilApi($jsonIdSocio);

            array_push($socios, $infoSocio[0]);
        }
    }
    else{
        $nombreTarea = -1;
    }

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('enviarTareaSocio.html', ['nombreTarea' => $nombreTarea, 'creador' => $creador, 'socios' =>$socios, 'rol' => $rol, 'img' => fotoPerfil($_SESSION['usuario']) ,'error' => $error, 'code' => $codigo]);
?>
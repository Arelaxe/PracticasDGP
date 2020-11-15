<?php
    require_once '../vendor/autoload.php';
    include_once("php/listasVinculaciones.php");
    require_once 'php/mostrarFotoPerfil.php';

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])) $rol = $_SESSION['rol'];
    else $rol = "";

    if (isset($_GET['facilitadorACargo'])) {
        $infoPerfil = array();
        $infoPerfil['facilitadorACargo'] = $_GET['facilitadorACargo'];
        $infoPerfil['nombreGrupo'] = $_GET['nombre'];

        $jsonInfoPerfil = json_encode($infoPerfil);

        $infoGrupo = infoGrupoApi($jsonInfoPerfil);

    } else{
        $idUsuario = -1;
    }

    

    echo $twig->render('perfilGrupo.html', ['infoGrupo' =>  $infoGrupo[0] , 'rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario'])]);
?>
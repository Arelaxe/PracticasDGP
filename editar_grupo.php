<?php
    require_once '../vendor/autoload.php';
    require_once "operaciones_api/autenticacionApi.php";

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
    }
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

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('editargrupo.html',['infoGrupo' =>  $infoGrupo[0] ,'rol' => $rol]);
?>
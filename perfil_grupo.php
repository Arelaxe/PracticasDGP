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

        $socios = array();

        foreach ($infoGrupo[0]->socios as $socio){
            $s_array = array();
            $s_array['username'] = $socio;
            $jsonInfoSocio = json_encode($s_array);
            $infoSocio = infoPerfilApi($jsonInfoSocio);
            array_push($socios, $infoSocio[0]);
        }
    } else{
        $idUsuario = -1;
    }

    

    echo $twig->render('perfilGrupo.html', ['infoGrupo' =>  $infoGrupo[0] , 'rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario']), 'socios' => $socios]);
?>
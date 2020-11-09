<?php
    require_once '../vendor/autoload.php';
    include_once("php/listasVinculaciones.php");

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])) $rol = $_SESSION['rol'];
    else $rol = "";

    if (isset($_GET['username'])) {
        $idUsuario = $_GET['username'];
        //$_SESSION['username'] = $idUsuario;

        $infoPerfil = array();
        $infoPerfil['username'] = $idUsuario;

        $jsonInfoPerfil = json_encode($infoPerfil);

        $result = infoPerfilApi($jsonInfoPerfil);

        $infoUsuario = array();
        $listadoSocios = array();

        foreach($result as $item){
            $infoUsuario['nombre'] = $item->nombre;
            $infoUsuario['username'] = $item->username;
            $infoUsuario['rol'] = $item->rol;
            if($infoUsuario['rol'] == "admin" || $infoUsuario['rol'] == "ambos" || $infoUsuario['rol'] == "facilitador"){
                $infoUsuario['direccion'] = $item->direccion;
                $infoUsuario['telefono'] = $item->telefono;

                $infoSociosVinculados = array();
                $infoSociosVinculados['user_facilitador'] = $infoUsuario['username'] ;
                $jsonInfoSociosVinculados = json_encode($infoSociosVinculados);

                $listadoSocios = listadoSociosVinculados($jsonInfoSociosVinculados);
            }
            else{

            }
    }
    } else{
        $idUsuario = -1;
    }

    

    echo $twig->render('perfil.html', ['id' => $idUsuario, 'infoUsuario' => $infoUsuario, 'rol' => $rol, 'sociosacargo' => $listadoSocios]);
?>
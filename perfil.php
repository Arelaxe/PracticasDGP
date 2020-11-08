<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if (isset($_GET['username'])) {
        $idUsuario = $_GET['username'];
        $_SESSION['username'] = $idUsuario;

        $infoPerfil = array();
        $infoPerfil['username'] = $idUsuario;

        $jsonInfoPerfil = json_encode($infoPerfil);

        $result = infoPerfilApi($jsonInfoPerfil);

        $infoUsuario = array();

        foreach($result as $item){
            $infoUsuario['nombre'] = $item->nombre;
            $infoUsuario['username'] = $item->username;
            $infoUsuario['rol'] = $item->rol;
            $infoUsuario['direccion'] = $item->direccion;
            $infoUsuario['telefono'] = $item->telefono;
    }
    } else{
        $idUsuario = -1;
    }

    

    echo $twig->render('perfil.html', ['id' => $idUsuario, 'nombre' => $infoUsuario['nombre'], 'username' => $infoUsuario['username'], 'rol' => $infoUsuario['rol'], 'direccion' => $infoUsuario['direccion'], 'telefono' => $infoUsuario['telefono']]);
?>
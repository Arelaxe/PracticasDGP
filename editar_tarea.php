<?php
    require_once '../vendor/autoload.php';
    require_once "operaciones_api/autenticacionApi.php";
    require_once "guardarImagen.php";
    require_once 'php/mostrarFotoPerfil.php';

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
    }
    else $rol = "";

    if (isset($_GET['nombre'])) {
        $idTarea = array();
        $idTarea['nombre'] = $_GET['nombre'];
        $idTarea['creador'] = $_SESSION['usuario'];

        $jsonIdTarea = json_encode($idTarea);

        $result = infoTareaApi($jsonIdTarea);

        $result = json_decode($result);

        $infoTarea = array();
        $infoTarea['nombre'] = $result[0]->nombre;
        $infoTarea['descripcion'] = $result[0]->descripcion;
        getImage($result[0]->fotoTarea);
        $infoTarea['fotoTarea'] = "img/".$result[0]->fotoTarea;
        getImage($result[0]->videoTarea);
        $infoTarea['videoTarea'] = "img/".$result[0]->videoTarea;
        getImage($result[0]->audioTarea);
        $infoTarea['audioTarea'] = "img/".$result[0]->audioTarea;
    } 
    else{
        $idUsuario = -1;
    }

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('editarTarea.html',['infoTarea' =>  $infoTarea ,'rol' => $rol, /'img' => fotoPerfil($_SESSION['usuario']), 'cuenta' => $_SESSION['usuario']]);
?>
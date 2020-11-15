<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';
    include_once("guardarImagen.php");

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])) $rol = $_SESSION['rol'];
    else $rol = "";

    if (isset($_GET['nombre'])) {
        $idUsuario = $_SESSION['usuario'];

        $idTarea = array();
        $idTarea['nombre'] = $_GET['nombre'];
        $idTarea['creador'] = $idUsuario;

        $jsonIdTarea = json_encode($idTarea);

        $result = infoTareaApi($jsonIdTarea);

        $result = json_decode($result);

        $infoTarea = array();

        $infoTarea['nombre'] = $result[0]->nombre;
        $infoTarea['creador'] = $result[0]->creador;
        $infoTarea['descripcion'] = $result[0]->descripcion;
        $infoTarea['fechaCreacion'] = $result[0]->fechaCreacion;
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

    echo $twig->render('informacionTarea.html',['rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario'])]);
?>

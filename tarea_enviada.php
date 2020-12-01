<?php
    require_once '../vendor/autoload.php';
    require_once 'php/mostrarFotoPerfil.php';
    include_once("guardarImagen.php");

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])) $rol = $_SESSION['rol'];
    else $rol = "";

    if (isset($_GET['nombre']) && isset($_GET['socio']) && isset($_GET['fechaEntrega'])) {
        $idUsuario = $_SESSION['usuario'];

        $idTarea = array();
        $idTarea['nombre'] = $_GET['nombre'];
        isset($_GET['creador']) ? $idTarea['creador'] = $_GET['creador'] : $idTarea['creador'] = $idUsuario;

        //Información de la tarea enviada

        $jsonIdTarea = json_encode($idTarea);

        $result = infoTareaApi($jsonIdTarea);

        $result = json_decode($result);

        $infoTarea = array();

        $infoTarea['nombre'] = $result[0]->nombre;
        $infoTarea['creador'] = $result[0]->creador;
        $infoTarea['descripcion'] = $result[0]->descripcion;
        getImage($result[0]->fotoTarea);
        $infoTarea['fotoTarea'] = "img/".$result[0]->fotoTarea;
        getImage($result[0]->videoTarea);
        $infoTarea['videoTarea'] = "img/".$result[0]->videoTarea;
        getImage($result[0]->audioTarea);
        $infoTarea['audioTarea'] = "img/".$result[0]->audioTarea;

        // Información de asignación de tarea

        $infoAsignacion = array();
        $infoAsignacion['nombreTarea'] = $_GET['nombre'];
        $infoAsignacion['creador'] = $idUsuario;
        $infoAsignacion['socioAsignado'] = $_GET['socio'];
        $infoAsignacion['fechaEntrega'] = $_GET['fechaEntrega'];

        $jsonInfoAsignacion = json_encode($infoAsignacion);
        $resultadoAsignacion = infoTareaEnviadaApi($jsonInfoAsignacion) ;

        $entrega = array();

        foreach ($resultadoAsignacion as $asignacion){
            $entrega['socio'] = $asignacion->socioAsignado;
            $entrega['fechaEnvio'] = $asignacion->fechaEntrega;
            $entrega['fechaLimiteEntrega'] = $asignacion->fechaLimiteEntrega;
            $entrega['permiteAudio'] = $asignacion->permiteAudio;
            $entrega['permiteVideo'] = $asignacion->permiteVideo;
            $entrega['permiteTexto'] = $asignacion->permiteTexto;
            if ($entrega['permiteAudio'] || $entrega['permiteVideo']){
                getImage($asignacion->respuesta);
                $entrega['respuesta'] = "img/" . $asignacion->respuesta ;
            }
            else $entrega['respuesta'] = $asignacion->respuesta;

            $infoSocio = array();
            $infoSocio['username'] = $asignacion->socioAsignado ;
            $jsonInfoSocio = json_encode($infoSocio) ;
            $resultado = infoPerfilApi($jsonInfoSocio);

            foreach ($resultado as $socio){
                $entrega['nombreSocio'] = $socio->nombre;
            }
        }

    } 
    else{
        $idUsuario = -1;
    }

    echo $twig->render('tareaEnviada.html',['infoTarea' => $infoTarea ,'rol' => $rol, 'cuenta' => $_SESSION['usuario'], 'img' => fotoPerfil($_SESSION['usuario']), 'entrega' => $entrega]);
?>

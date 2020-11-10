<?php
    require_once '../vendor/autoload.php';
    include_once("operaciones_api/autenticacionApi.php");
    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    session_start();

    if(isset($_SESSION['usuario'])){
        $infoFacilitador = array();
        $infoFacilitador['username'] = $_SESSION['usuario'];
        $jsoninfoFacilitador = json_encode($infoFacilitador);
        $listado = misGruposApi($jsoninfoFacilitador);
        $rol = $_SESSION['rol'];

        $listado_grupos = array();

        foreach($listado as $grupo){
            $infoGrupo = array();

            $infoGrupo['facilitador'] = $grupo->facilitadorACargo;
            $infoGrupo['nombre'] = $grupo->nombre;

            array_push($listado_grupos, $infoGrupo);
		}
    }

    echo $twig->render('misgrupos.html', ['listadoGrupos' => $listado_grupos, 'rol' => $rol]);
?>
<?php
    require_once '../vendor/autoload.php';
    require_once('operaciones_api/autenticacionApi.php');

    session_start();
    if(isset($_SESSION['usuario'])){
        $rol = $_SESSION['rol'];
        if (isset($_GET['grupo'])){
            $socio = -1;
            $grupo = $_GET['grupo'];
            $listaNombres = array();
            $listaNombres['username'] = $_SESSION['usuario'];
            $jsonListaNombres = json_encode($listaNombres);
            $listaSocios = misSociosApi($jsonListaNombres);

            $socios = array();

            foreach ($listaSocios as $s){
                $esta_en_grupo = false;
                $s_array = array();
                $s_array['username'] = $s;
                $json_s_array = json_encode($s_array);
                $s_info = obtenerInfoUsuariosApi($json_s_array);
                var_dump($s_info);
                foreach ($s_info->grupos as $g){
                    if ($g == $grupo){
                        $esta_en_grupo = true;
                    }
                }

                if (!$esta_en_grupo){
                    array_push($socios, $socio);
                }
            }
        }
        else if (isset($_GET['socio'])){

        }
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('anadirSocioGrupo.html',['rol' => $rol, 'grupo' => $grupo, 'socios' => $socios, 'socio' => $socio]);
?>
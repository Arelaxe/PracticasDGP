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
                foreach ($s_info[0]->grupos as $g){
                    if ($g == $grupo){
                        $esta_en_grupo = true;
                    }
                }

                if (!$esta_en_grupo){
                    array_push($socios, $s_info[0]);
                }
            }
        }
        else if (isset($_GET['socio'])){
            $grupo = -1;
            $socio = $_GET['socio'];
            $s_array = array();
            $s_array['username'] = $s;
            $json_s_array = json_encode($s_array);
            $s_info = obtenerInfoUsuariosApi($json_s_array);
            
            $listaNombres = array();
            $listaNombres['username'] = $_SESSION['usuario'];
            $jsonListaNombres = json_encode($listaNombres);
            $listaGrupos = misGruposApi($jsonListaNombres);
            
            foreach ($listaGrupos as $g){
                $esta_en_grupo = false;
                $grupos_socio = array();
                $grupos_socio = $s_info[0]->grupos;
            }
        }
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('anadirSocioGrupo.html',['rol' => $rol, 'grupo' => $grupo, 'socios' => $socios, 'socio' => $socio, 's_info' => $s_info[0]]);
?>
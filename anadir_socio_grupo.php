<?php
    require_once '../vendor/autoload.php';
    require_once('operaciones_api/autenticacionApi.php');
    $grupos = array();
    $socios = array();
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
            $s = $_GET['socio'];
            $s_array = array();
            $s_array['username'] = $s;
            $json_s_array = json_encode($s_array);
            $s_info = obtenerInfoUsuariosApi($json_s_array);
            $gruposSocio = $s_info[0]->grupos;

            $socio = array();
            $socio['username'] = $s;
            $socio['nombre'] = $s_info[0]->nombre;
            
            $listaNombres = array();
            $listaNombres['username'] = $_SESSION['usuario'];
            $jsonListaNombres = json_encode($listaNombres);
            $listaGrupos = misGruposApi($jsonListaNombres);
            
            foreach ($listaGrupos as $g){
                $esta_en_grupo = false;
                $grupos_socio = array();

                foreach ($gruposSocio as $gs){
                    if ($g->nombre == $gs){
                        $esta_en_grupo = true;
                    }
                }

                if (!$esta_en_grupo){
                    array_push($grupos, $g);
                }
            }
        }
    }
    else $rol = "";

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('anadirSocioGrupo.html',['rol' => $rol, 'grupo' => $grupo, 'socios' => $socios, 'socio' => $socio, 's_info' => $s_info[0], 'grupos' => $grupos]);
?>
<?php
        include_once("operaciones_api/autenticacionApi.php");

        //Lista de socios no vinculados
    function listadoSociosNoVinculados($jsonInfo){
        $listaSocios = listadoSociosNoVinculadosApi($jsonInfo);

        $listaInfoSocios = array();        
        foreach($listaSocios as $i){
            $infoUsuario = $i;
            $jsonInfoUsuario = json_encode($infoUsuario);
            $infoUsuario = obtenerInfoUsuariosApi($jsonInfoUsuario);
            foreach($infoUsuario as $ocurrencia){
                array_push($listaInfoSocios,$ocurrencia);
            }
        }
        return $listaInfoSocios ;
    }

    //Lista de socios vinculados
    function listadoSociosVinculados($jsonInfo){
        $listaSocios = listadoSociosVinculadosApi($jsonInfo)[0]->sociosACargo;

        $listaInfoSocios = array();        
        foreach($listaSocios as $i){
            $infoUsuario = array();
            $infoUsuario['username'] = $i;
            $jsonInfoUsuario = json_encode($infoUsuario);
            $infoUsuario = obtenerInfoUsuariosApi($jsonInfoUsuario);
            foreach($infoUsuario as $ocurrencia){
                array_push($listaInfoSocios,$ocurrencia);
            }
        }
        return $listaInfoSocios ;
    }

?>
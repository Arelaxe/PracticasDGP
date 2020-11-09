<?php
    
    function listadoSociosNoVinculados($jsonInfo){
        $listaSocios = listadoSociosNoVinculadosApi($jsonInfo);
        var_dump($listaSocios);
        foreach($listaSocios as $i){
            $i = obtenerInfoUsuariosApi(json_encode($i['username']));
        }
        return $listaSocios ;
    }

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
<?php
    require_once "../operaciones_api/autenticacionApi.php";
    session_start();

    if(isset($_SESSION['usuario']) && isset($_POST['nombre']) && isset($_POST['facilitadorACargo'])){
        $infoGrupoPeticion = array();
        $infoGrupoPeticion['nombreGrupo'] = $_POST['nombre'] ;
        $infoGrupoPeticion['facilitadorACargo'] = $_POST['facilitadorACargo'] ;
        $jsonPeticion = json_encode($infoGrupoPeticion);
        $result = infoGrupoApi($jsonPeticion);
        foreach($result as $item){
            $infoGrupoEditado = array();
            $infoGrupoEditado['nombre'] = $item->nombre;
            $infoGrupoEditado['facilitadorACargo'] = $item->facilitadorACargo;
            $infoGrupoEditado['socios'] = $item->socios;
            $infoGrupoEditado['descripcion'] = $_POST['descripcion'];

            $jsonInfoGrupoEditado = json_encode($infoGrupoEditado);
            $resultado = editarGrupoApi($jsonInfoGrupoEditado);
        }
        header('Location: ../mis_grupos.php');
    }
    else {
        print ("Nope");
    }
?>
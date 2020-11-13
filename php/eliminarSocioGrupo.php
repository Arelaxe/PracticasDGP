<?php
    require_once "../operaciones_api/autenticacionApi.php";
    session_start();
    if(isset($_SESSION['usuario'])){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $vinculacion = array();
            $vinculacion['facilitadorACargo'] = $_SESSION['usuario'];
            $vinculacion['user_socio'] = $_POST['socioEliminar'];
            $vinculacion['nombre_grupo'] = $_POST['grupoEliminar'];
            $jsonVinculacion = json_encode($vinculacion);
            eliminarSocioGrupoApi($jsonVinculacion);

            header('Location: ../perfil_grupo.php?nombre='.$vinculacion['nombre_grupo'].'&facilitadorACargo='.$vinculacion['facilitadorACargo']);
        }
    }
?>
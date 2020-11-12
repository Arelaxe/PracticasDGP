<?php
    require_once "../operaciones_api/autenticacionApi.php";

    if(isset($_POST['username'])){
        var_dump($_POST);
        $infoUsuario = array();
        $infoUsuario['username'] = $_POST['username'];
        $infoUsuario = infoPerfilApi(json_encode($infoUsuario));
        var_dump($infoUsuario);

    }
?>
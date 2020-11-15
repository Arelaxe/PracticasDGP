<?php
    include_once("operaciones_api/autenticacionApi.php");

    function saveFile($file){
        if($_SERVER['REQUEST_METHOD'] === "POST"){
            $location = "../img/";
            if(isset($_FILES[$file]))
            {
                $Image = $_FILES[$file]['tmp_name'];
                $ImageContent = addslashes(file_get_contents($Image));
                $ImagePosted =true;

                $ImageRealName = $_FILES[$file]['name'];
                if(move_uploaded_file($Image, $location.$ImageRealName))
                {
                    return $ImageRealName;
                }
                else{
                    return null;
                }
            }
        }
    }

    function getImage($imagen){
        if($imagen != ""){
            $ruta = array();
            $ruta['ruta'] = $imagen;
            $jsonruta = json_encode($ruta);
            $result = getImageApi($jsonruta);
            $result = base64_decode($result);
            file_put_contents("img/".$ruta['ruta'], $result);
        }
    }

?>

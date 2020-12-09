<?php
function registroApi($jsoninfoRegistro){
    // API URL
    $url = 'http://localhost:5000/registro';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoRegistro);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    print($result);
    return $result;
}

function inicioSesionApi($jsonInfoLogin){
     // API URL
     $url = 'http://localhost:5000/login';

     // Create a new cURL resource
     $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoLogin);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     // Close cURL resource
     curl_close($ch);
     print($result);
     return $result;
}

function listadoAdministradoresApi(){
    // API URL
    $url = 'http://localhost:5000/listado-administradores';

    $response = file_get_contents($url);
    $response = json_decode($response);

    return $response;
}

function listadoFacilitadoresApi(){
    // API URL
     $url = 'http://localhost:5000/listado-facilitadores';

     $response = json_decode(file_get_contents($url));
     return $response;
}

function listadoSociosApi(){
    // API URL
     $url = 'http://localhost:5000/listado-socios';

     $response = json_decode(file_get_contents($url));
     return $response;
}

function infoPerfilApi($jsonInfoPerfil){
    // API URL
     $url = 'http://localhost:5000/perfil';

     // Create a new cURL resource
     $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoPerfil);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     // Close cURL resource
     curl_close($ch);
     
     $result = json_decode($result);

     return $result;
}

function listadoTareasApi($jsonInfoFacilitador){
    // API URL
     $url = 'http://localhost:5000/listado-tareas';

      // Create a new cURL resource
     $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     // Close cURL resource
     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function tareasEnviadasApi($jsonInfoFacilitador){
    // API URL
     $url = 'http://localhost:5000/tareas-enviadas';

      // Create a new cURL resource
     $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     // Close cURL resource
     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function misGruposApi($jsonInfoFacilitador){
    // API URL
     $url = 'http://localhost:5000/mis-grupos';

      // Create a new cURL resource
     $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     // Close cURL resource
     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function infoGrupoApi($jsonInfo){
    // API URL
     $url = 'http://localhost:5000/info-grupo';

      // Create a new cURL resource
     $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     // Close cURL resource
     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function misSociosApi($jsonInfoFacilitador){
    // API URL
     $url = 'http://localhost:5000/mis-socios';

      // Create a new cURL resource
     $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     // Close cURL resource
     curl_close($ch);
     $result = json_decode($result);
     $result = $result[0]->sociosACargo;
     return $result;
}

function eliminarUsuarioApi($jsonInfoUsuario){
    // API URL
    $url = 'http://localhost:5000/eliminar-usuario';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);
   $result = json_decode($result);
   return $result;
}


function listadoSociosNoVinculadosApi($jsonInfoUsuario){
    // API URL
    $url = 'http://localhost:5000/socios-no-vinculados';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function listadoSociosVinculadosApi($jsonInfoUsuario){
    // API URL
    $url = 'http://localhost:5000/socios-vinculados';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function obtenerInfoUsuariosApi($jsonInfoUsuario){
      // API URL
      $url = 'http://localhost:5000/info-usuario';

      // Create a new cURL resource
     $ch = curl_init($url);
  
     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);
  
     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
  
     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  
     // Execute the POST request
     $result = curl_exec($ch);
  
     // Close cURL resource
     curl_close($ch);
     $result = json_decode($result);
     return $result;  
}

function vincularSocioApi($jsonInfoUsuario){
    /*VINCULAR FACILITADOR A SOCIO*/

    // API URL
    $url = 'http://localhost:5000/vincular-facilitador-socio';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);

    /*VINCULAR SOCIO A FACILITADOR*/

    // API URL
    $url = 'http://localhost:5000/vincular-socio-facilitador';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
}

function desvincularSocioApi($jsonInfoUsuario){
    /* DESVINCULAR FACILITADOR CON SOCIO */

    // API URL
    $url = 'http://localhost:5000/desvincular-facilitador-socio';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    curl_exec($ch);

    // Close cURL resource
    curl_close($ch);

    /* DESVINCULAR SOCIO CON FACILITADOR */

    // API URL
    $url = 'http://localhost:5000/desvincular-socio-facilitador';

     // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
}

function crearGrupoApi($jsonInfoGrupo){
    // API URL
    $url = 'http://localhost:5000/crear-grupo';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoGrupo);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function eliminarGrupoApi($jsonInfoGrupo){
    // API URL
    $url = 'http://localhost:5000/eliminar-grupo';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoGrupo);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);

   return $result;
}


function editarUsuarioApi($jsonInfo){
    // API URL
    $url = 'http://localhost:5000/editarUsuario';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);

   return $result;
}

function editarGrupoApi($jsonInfo){
    // API URL
    $url = 'http://localhost:5000/editarGrupo';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);

   return $result;
}

function anadirSocioGrupoApi($jsonInfoUsuarios){
    /*AÑADIENDO SOCIO A GRUPO*/

    // API URL
    $url = 'http://localhost:5000/anadir-socio-grupo';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);

    /*VINCULAR GRUPO A SOCIO*/

    // API URL
    $url = 'http://localhost:5000/anadir-grupo-socio';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
}

function eliminarSocioGrupoApi($jsonInfoUsuarios){
    /*AÑADIENDO SOCIO A GRUPO*/

    // API URL
    $url = 'http://localhost:5000/eliminar-socio-grupo';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);
    var_dump($result);
    // Close cURL resource
    curl_close($ch);

    /*VINCULAR GRUPO A SOCIO*/

    // API URL
    $url = 'http://localhost:5000/eliminar-grupo-socio';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);
    var_dump($result);
    // Close cURL resource
    curl_close($ch);
}

function sendFileApi($imageRealName){
    $location = "../img/";
    $url = "http://localhost:5000/upload";
    $filename = $imageRealName;
    $filedata = $location.$imageRealName;
    if ($filedata != ''){
        $jsonInfo = array();
        $jsonInfo['filedata'] = base64_encode(file_get_contents($filedata));
        $jsonInfo['filename'] = $imageRealName;

        $jsonInfo = json_encode($jsonInfo);
      // API URL
        $url = 'http://localhost:5000/upload';

        // Create a new cURL resource
        $ch = curl_init($url);

        // Attach encoded JSON string to the POST fields
        curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);

        // Set the content type to application/json
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

        // Return response instead of outputting
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

        // Execute the POST request
        $result = curl_exec($ch);
        // Close cURL resource
        curl_close($ch);

    }
    else
    {
        $errmsg = "Please select the file";
    }
}

function getImageApi ($infoImagen){
    // API URL
    $url = 'http://localhost:5000/get-image';

    $ch = curl_init($url);

     // Attach encoded JSON string to the POST fields
     curl_setopt($ch, CURLOPT_POSTFIELDS, $infoImagen);

     // Set the content type to application/json
     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

     // Return response instead of outputting
     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

     // Execute the POST request
     $result = curl_exec($ch);

     return $result;
}

function eliminarTareaApi($jsonInfoUsuario){
    // API URL
    $url = 'http://localhost:5000/eliminar-tarea';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function eliminarTareaEnviadaApi($jsonInfoTarea){
    // API URL
    $url = 'http://localhost:5000/eliminar-tarea-enviada';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoTarea);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);

   // Close cURL resource
   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function infoTareaEnviadaApi($jsonInfo){
    // API URL
    $url = 'http://localhost:5000/info-tarea-enviada';

    // Create a new cURL resource
   $ch = curl_init($url);

   // Attach encoded JSON string to the POST fields
   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);

   // Set the content type to application/json
   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   // Return response instead of outputting
   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   // Execute the POST request
   $result = curl_exec($ch);
   // Close cURL resource
   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function crearTareaApi($jsoninfoTarea){
    // API URL
    $url = 'http://localhost:5000/crear-tarea';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    
    return $result;
}

function infoTareaApi($jsoninfoTarea){
    // API URL
    $url = 'http://localhost:5000/info-tarea';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    
    return $result;
}

function editarTareaApi($jsoninfoTarea){
    // API URL
    $url = 'http://localhost:5000/editar-tarea';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    
    return $result;
}

function listadoTareasAdminApi(){
    // API URL
     $url = 'http://localhost:5000/listado-tareas-admin';

     $response = json_decode(file_get_contents($url));
     return $response;
}

function enviarTareaApi($jsoninfoTarea){
    // API URL
    $url = 'http://localhost:5000/enviar-tarea';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    print($result);
    return $result;
}

function infoChatApi(){
    // API URL
    $url = 'http://localhost:5000/info-chat';

    $response = file_get_contents($url);
    $response = json_decode($response);

    return $response;
}

function borrarChatApi($jsonInfoChat){
    // API URL
    $url = 'http://localhost:5000/borrar-chat';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoChat);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    return $result;
}

function nuevoMensajeWebApi($jsoninfoTarea){
    // API URL
    $url = 'http://localhost:5000/nuevo-mensaje-web';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    return $result;
}

function vistaTareaWebApi($jsoninfoTarea){
    // API URL
    $url = 'http://localhost:5000/tarea-vista-web';

    // Create a new cURL resource
    $ch = curl_init($url);

    // Attach encoded JSON string to the POST fields
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

    // Set the content type to application/json
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);

    // Close cURL resource
    curl_close($ch);
    return $result;
}

?>

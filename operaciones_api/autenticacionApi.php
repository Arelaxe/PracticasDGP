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

?>
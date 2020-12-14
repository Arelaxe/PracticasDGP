<?php
function registroApi($jsoninfoRegistro){
    // URL de la endpoint de la API
    $url = 'http://localhost:5000/registro';

    // Creamos un nuevo recurso CURL
    $ch = curl_init($url);

    // Aniaadimos el JSON recibido por parametro a la peticion POST
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoRegistro);

    // Cambiamos el tipo de contenido de la peticion POST a JSON
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Ejecutamos la petición POST
    $result = curl_exec($ch);

    // Y cerramos
    curl_close($ch);
    print($result);
    return $result;
}

function inicioSesionApi($jsonInfoLogin){

     $url = 'http://localhost:5000/login';


     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoLogin);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     print($result);
     return $result;
}

function listadoAdministradoresApi(){

    $url = 'http://localhost:5000/listado-administradores';

    $response = file_get_contents($url);
    $response = json_decode($response);

    return $response;
}

function listadoFacilitadoresApi(){

     $url = 'http://localhost:5000/listado-facilitadores';

     $response = json_decode(file_get_contents($url));
     return $response;
}

function listadoSociosApi(){

     $url = 'http://localhost:5000/listado-socios';

     $response = json_decode(file_get_contents($url));
     return $response;
}

function infoPerfilApi($jsonInfoPerfil){

     $url = 'http://localhost:5000/perfil';


     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoPerfil);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     
     $result = json_decode($result);

     return $result;
}

function listadoTareasApi($jsonInfoFacilitador){

     $url = 'http://localhost:5000/listado-tareas';

 
     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function tareasEnviadasApi($jsonInfoFacilitador){

     $url = 'http://localhost:5000/tareas-enviadas';

 
     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function notificacionesTareasApi($jsonInfoFacilitador){

     $url = 'http://localhost:5000/tareas-con-notificaciones';

 
     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function misGruposApi($jsonInfoFacilitador){

     $url = 'http://localhost:5000/mis-grupos';

 
     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function infoGrupoApi($jsonInfo){

     $url = 'http://localhost:5000/info-grupo';

 
     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     $result = json_decode($result);
     return $result;
}

function misSociosApi($jsonInfoFacilitador){

     $url = 'http://localhost:5000/mis-socios';

 
     $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoFacilitador);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);


     curl_close($ch);
     $result = json_decode($result);
     $result = $result[0]->sociosACargo;
     return $result;
}

function eliminarUsuarioApi($jsonInfoUsuario){

    $url = 'http://localhost:5000/eliminar-usuario';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);
   $result = json_decode($result);
   return $result;
}


function listadoSociosNoVinculadosApi($jsonInfoUsuario){

    $url = 'http://localhost:5000/socios-no-vinculados';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function listadoSociosVinculadosApi($jsonInfoUsuario){

    $url = 'http://localhost:5000/socios-vinculados';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function obtenerInfoUsuariosApi($jsonInfoUsuario){
 
      $url = 'http://localhost:5000/info-usuario';

 
     $ch = curl_init($url);
  

     curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);
  

     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
  

     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  

     $result = curl_exec($ch);
  

     curl_close($ch);
     $result = json_decode($result);
     return $result;  
}

function vincularSocioApi($jsonInfoUsuario){
    /*VINCULAR FACILITADOR A SOCIO*/


    $url = 'http://localhost:5000/vincular-facilitador-socio';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);

    /*VINCULAR SOCIO A FACILITADOR*/


    $url = 'http://localhost:5000/vincular-socio-facilitador';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
}

function desvincularSocioApi($jsonInfoUsuario){
    /* DESVINCULAR FACILITADOR CON SOCIO */


    $url = 'http://localhost:5000/desvincular-facilitador-socio';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    curl_exec($ch);

 
    curl_close($ch);

    /* DESVINCULAR SOCIO CON FACILITADOR */


    $url = 'http://localhost:5000/desvincular-socio-facilitador';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    curl_exec($ch);

 
    curl_close($ch);
}

function crearGrupoApi($jsonInfoGrupo){

    $url = 'http://localhost:5000/crear-grupo';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoGrupo);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function eliminarGrupoApi($jsonInfoGrupo){

    $url = 'http://localhost:5000/eliminar-grupo';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoGrupo);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);

   return $result;
}


function editarUsuarioApi($jsonInfo){

    $url = 'http://localhost:5000/editarUsuario';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);

   return $result;
}

function editarGrupoApi($jsonInfo){

    $url = 'http://localhost:5000/editarGrupo';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);

   return $result;
}

function anadirSocioGrupoApi($jsonInfoUsuarios){
    /*AÑADIENDO SOCIO A GRUPO*/


    $url = 'http://localhost:5000/anadir-socio-grupo';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);

    /*VINCULAR GRUPO A SOCIO*/


    $url = 'http://localhost:5000/anadir-grupo-socio';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
}

function eliminarSocioGrupoApi($jsonInfoUsuarios){
    /*AÑADIENDO SOCIO A GRUPO*/


    $url = 'http://localhost:5000/eliminar-socio-grupo';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);
    var_dump($result);
 
    curl_close($ch);

    /*VINCULAR GRUPO A SOCIO*/


    $url = 'http://localhost:5000/eliminar-grupo-socio';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuarios);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);
    var_dump($result);
 
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
 
        $url = 'http://localhost:5000/upload';

   
        $ch = curl_init($url);

   
        curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);

   
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

   
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

   
        $result = curl_exec($ch);
   
        curl_close($ch);

    }
    else
    {
        $errmsg = "Please select the file";
    }
}

function getImageApi ($infoImagen){

    $url = 'http://localhost:5000/get-image';

    $ch = curl_init($url);


     curl_setopt($ch, CURLOPT_POSTFIELDS, $infoImagen);


     curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


     curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


     $result = curl_exec($ch);

     return $result;
}

function eliminarTareaApi($jsonInfoUsuario){

    $url = 'http://localhost:5000/eliminar-tarea';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoUsuario);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function eliminarTareaEnviadaApi($jsonInfoTarea){

    $url = 'http://localhost:5000/eliminar-tarea-enviada';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoTarea);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);


   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function infoTareaEnviadaApi($jsonInfo){

    $url = 'http://localhost:5000/info-tarea-enviada';


   $ch = curl_init($url);


   curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfo);


   curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));


   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);


   $result = curl_exec($ch);

   curl_close($ch);
   $result = json_decode($result);
   return $result;
}

function crearTareaApi($jsoninfoTarea){

    $url = 'http://localhost:5000/crear-tarea';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    
    return $result;
}

function infoTareaApi($jsoninfoTarea){

    $url = 'http://localhost:5000/info-tarea';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    
    return $result;
}

function editarTareaApi($jsoninfoTarea){

    $url = 'http://localhost:5000/editar-tarea';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    
    return $result;
}

function listadoTareasAdminApi(){

     $url = 'http://localhost:5000/listado-tareas-admin';

     $response = json_decode(file_get_contents($url));
     return $response;
}

function enviarTareaApi($jsoninfoTarea){

    $url = 'http://localhost:5000/enviar-tarea';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    print($result);
    return $result;
}

function infoChatApi(){

    $url = 'http://localhost:5000/info-chat';

    $response = file_get_contents($url);
    $response = json_decode($response);

    return $response;
}

function borrarChatApi($jsonInfoChat){

    $url = 'http://localhost:5000/borrar-chat';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonInfoChat);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    return $result;
}

function nuevoMensajeWebApi($jsoninfoTarea){

    $url = 'http://localhost:5000/nuevo-mensaje-web';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    return $result;
}

function vistaTareaWebApi($jsoninfoTarea){

    $url = 'http://localhost:5000/tarea-vista-web';
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);

    curl_close($ch);
    return $result;
}

function tareasEnviadasSocioApi($jsoninfoTarea){

    $url = 'http://localhost:5000/tareas-enviadas-socio';
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);

    curl_close($ch);
    return $result;
}

function infoMDApi($jsoninfoMD){

    $url = 'http://localhost:5000/info-md';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoMD);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    return $result;
}

function establecerMDApi($jsoninfoMD){

    $url = 'http://localhost:5000/establecer-md';


    $ch = curl_init($url);

 
    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoMD);

 
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

 
    $result = curl_exec($ch);

 
    curl_close($ch);
    return $result;
}

function eliminarMDApi($jsoninfoTarea){

    $url = 'http://localhost:5000/eliminar-md';
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);

    curl_close($ch);
    return $result;
}

function notificarNuevoMDApi($jsoninfoTarea){

    $url = 'http://localhost:5000/notificar-md';
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);

    curl_close($ch);
    return $result;
}

function vistoMDApi($jsoninfoTarea){

    $url = 'http://localhost:5000/md-visto';
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_POSTFIELDS, $jsoninfoTarea);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);

    curl_close($ch);
    return $result;
}

?>

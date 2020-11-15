<?php
    //include_once("../operaciones_api/autenticacionApi.php");

    session_start();
    if($_SERVER['REQUEST_METHOD'] === "POST"){
        // API URL
     $url = 'http://localhost:5000/upload';

     // Create a new cURL resource
    $ch = curl_init($url);

    $location = "img/";
if(isset($_FILES["file"]))
 {
/*$checkImage = getimagesize($_FILES["file"]["tmp_name"]);
if($checkImage !== false){*/
    $Image = $_FILES['file']['tmp_name'];
    $ImageContent = addslashes(file_get_contents($Image));
    $ImagePosted =true;

    $ImageRealName = $_FILES['file']['name'];
    if(move_uploaded_file($Image, $location.$ImageRealName))
    {
        echo 'File Uploaded';
    }
//}
 }
else{
echo 'Image not submitted';
}

$url = "http://localhost:5000/upload";
$filename = $_FILES['file']['name'];
$filedata = $location.$ImageRealName;
$filesize = $_FILES['file']['size'];
if ($filedata != '')
{
    var_dump(base64_encode(file_get_contents($filedata)));
    $jsonInfo = array();
$jsonInfo['filedata'] = base64_encode(file_get_contents($filedata));
$jsonInfo['filename'] = $ImageRealName;

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

    // Set the content type to application/json
   /* curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:multipart/form-data'));

    // Return response instead of outputting
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Execute the POST request
    $result = curl_exec($ch);*/

    // Close cURL resource
    //curl_close($ch);
    }  

?>
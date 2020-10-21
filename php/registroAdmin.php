<?php
    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoRegistro = array();

        $infoRegistro['email'] = $_POST['email'];
        $infoRegistro['password'] = md5($_POST['pass']);
        $infoRegistro['rol'] = $_POST['rol'];

        $jsoninfoRegistro = json_encode($infoRegistro);

        var_dump($infoRegistro, $jsoninfoRegistro);

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
    }

?>
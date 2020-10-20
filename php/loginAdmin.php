<?php
    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $infoLogin = array();

        $infoLogin['email'] = $_POST['email'];
        $infoLogin['passwd'] = $_POST['pass'];

        $jsonInfoLogin = json_encode($infoLogin);

        var_dump($infoLogin, $jsonInfoLogin);

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

        //$data = json_decode(file_get_contents('php://input'), true);
    }

?>
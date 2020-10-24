<?php
    require_once 'C:\\xampp\\vendor\\autoload.php';

    $loader = new \Twig\Loader\FilesystemLoader('templates');
    $twig = new \Twig\Environment($loader);

    echo $twig->render('registro.html');
?>
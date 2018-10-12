<?php
    include_once(dirname(__FILE__)."./../../modelo/encriptacion/Encrypter.php");

    $cadena = $_POST['pass'];
    $encrypter = Encrypter::getInstance();
    $pass = $encrypter->encrypt($cadena, "hola");
    $original = $encrypter->decrypt($pass, "hola");

    echo $pass." ".$original;
?>
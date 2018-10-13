<?php
    require_once(dirname(__FILE__)."./../../modelo/encriptacion/Encrypter.php");

    $cadena = $_POST['pass'];
    $encrypter = Encrypter::getInstance();
    $pass = $encrypter->encrypt($cadena);
    $original = $encrypter->decrypt($pass);

    echo $pass." ".$original;
?>
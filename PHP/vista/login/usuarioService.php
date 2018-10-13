<?php
    require_once(dirname(__FILE__)."./../../controlador/login/LoginDAO.php");

    $nick = $_POST['nick'];
    $pass = $_POST['pass'];

    $result = LoginDAO::getInstance()->getUsuario($nick, $pass);
    echo json_encode($result);
?>
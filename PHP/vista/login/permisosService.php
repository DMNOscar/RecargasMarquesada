<?php
    require_once(dirname(__FILE__)."./../../controlador/login/LoginDAO.php");

    $result = LoginDAO::getInstance()->getPermisos();
    echo json_encode($result);
?>
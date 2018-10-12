<?php
    //Clase Singleton que contiene los métodos solicitados por Login
    class LoginDAO
    {
        private static $dao;

        private function __construct()
        {
        }

        public static function getInstance()
        {
            if(self::$dao == null)
                self::$dao = new LoginDAO();

            return self::$dao;
        }

        public function getUsuario($nick, $pass)
        {

        }

        public function __clone()
        {
            trigger_error("No se puede clonar la clase ".get_class(self::$dao));
        }
    }
?>
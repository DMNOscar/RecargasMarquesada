<?php
    //Clase Singleton que se encarga de conectar a la BD
    class Connection
    {
        private static $connection;
        private $db;

        private function __construct($host = null, $user = null, $pass = null, $name = null)
        {
            $host = $host == null ? "localhost" : $host;
            $user = $user == null ? "root" : $user;
            $pass = $pass == null ? "" : $pass;
            $name = $name == null ? "ultrachip" : $name;
            $port = $port == null ? "3306" : $port;

            $db = new mysqli($host, $user, $pass, $name, $port);
        }

        public static function getInstance($host = null, $user = null, $pass = null, $db = null, $port = null)
        {
            if(self::$connection == null)
                self::$connection = new Connection($host, $user, $pass, $db, $port);

            return self::$connection;
        }

        public function connectDB()
        {
            if($db->connect_error)
                die();
            else
            {
                $db->set_charset("utf8");
                return $db;
            }
        }

        public function __clone()
        {
            trigger_error("No se puede clonar la clase ".get_class(self::$connection));
        }
    }
?>
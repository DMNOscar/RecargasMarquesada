<?php
    require_once(dirname(__FILE__)."./../db/Connection.php");
    require_once(dirname(__FILE__)."./../../modelo/encriptacion/Encrypter.php");

    //Clase Singleton que contiene los métodos solicitados por Login
    class LoginDAO
    {
        private static $dao;
        private static $db;

        private function __construct()
        {
            self::$db = Connection::getInstance()->connectDB();
        }

        public static function getInstance()
        {
            if(self::$dao == null)
                self::$dao = new LoginDAO();

            return self::$dao;
        }

        public function getUsuario($nick, $pass)
        {
            $passEncriptado = Encrypter::getInstance()->encrypt($pass);

            $query = "SELECT u.id AS usuario_id, u.nombre, p.id AS permiso_id, p.nombre AS permiso
            FROM usuario u
            INNER JOIN credencial c ON c.usuario_id = u.id
            INNER JOIN permiso p ON u.permiso_id = p.id
            WHERE c.nick = '$nick'
            AND c.pass = '$passEncriptado'
            AND u.activo = TRUE";

            //Obtiene el único elemento de la consulta
            $result = mysqli_query(self::$db, $query);
            return mysqli_fetch_assoc($result);
        }

        public function getPermisos()
        {
            $query = "SELECT id, nombre
            FROM permiso
            WHERE activo = TRUE";

            //Obtiene la lista de elementos de los permisos
            $result = mysqli_query(self::$db, $query);
            $array = array();
            while($row = mysqli_fetch_assoc($result))
                $array[] = $row;
            return $array;
        }

        public function __clone()
        {
            trigger_error("No se puede clonar la clase ".get_class(self::$dao));
        }
    }
?>
using System;
using MySql.Data.MySqlClient;

namespace UltraChip.Conexion
{
    class Connection
    {
        private static Connection connection;
        private MySqlConnection mysql;
        private String cadenaConexion;

        private Connection()
        {
            cadenaConexion = "server=187.189.152.4;port=3306;username = xampp; password = marquesada?466; SslMode=none;database=recargasmarquesada";
        }

        public static Connection getInstance()
        {
            if (connection == null)
                connection = new Connection();

            connection.conectar();
            return connection;
        }

        //Conecta a la BD
        private void conectar()
        {
            desconectar();
            mysql = new MySqlConnection(cadenaConexion);
            mysql.Open();
        }

        public void desconectar()
        {
            try
            {
                mysql.Close();
            }
            catch (Exception e)
            {
                //throw new Exception(e.Message);
            }
        }

        public MySqlConnection getConnection()
        {
            return mysql;
        }

        public Boolean isConnection()
        {
            return mysql != null;
        }

        public String getCadenaConexion()
        {
            return cadenaConexion;
        }

        public void setCadenaConexion()
        {
            this.cadenaConexion = "server = 187.189.152.4; port = 3306; username = xampp; password = marquesada?466; SslMode=none;database = recargasmarquesada";
        }
    }
}

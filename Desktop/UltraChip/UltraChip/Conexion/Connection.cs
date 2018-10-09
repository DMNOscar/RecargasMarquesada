using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
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
            cadenaConexion = "server = 187.189.152.4; port = 3306; username = javiersl; password = javiersl; database = recargasmarquesada";
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
            catch (MySqlException e)
            {
                throw new Exception(e.Message);
            }
        }
    }
}

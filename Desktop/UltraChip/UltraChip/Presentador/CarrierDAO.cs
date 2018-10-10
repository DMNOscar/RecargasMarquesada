using System;
using System.Collections.Generic;
using MySql.Data.MySqlClient;
using UltraChip.Conexion;

namespace UltraChip.Presentador
{
    class CarrierDAO
    {
        private static CarrierDAO dao;

        private CarrierDAO()
        {
        }

        public static CarrierDAO getInstance()
        {
            if (dao == null)
                dao = new CarrierDAO();

            return dao;
        }

        public List<String> companias()
        {
            String query = "SELECT nombre FROM carrier;";
            List<String> lista = new List<string>();
            try
            {
                Connection.getInstance().setCadenaConexion();
                using (MySqlCommand cmd = new MySqlCommand(query, Connection.getInstance().getConnection()))
                {
                    MySqlDataReader reader;
                    cmd.Prepare();
                    cmd.CommandTimeout = 60;
                    using (reader = cmd.ExecuteReader())
                    {
                        if(reader.HasRows)
                        {
                            while (reader.Read())
                                lista.Add(reader.GetString(0));
                        }

                        Connection.getInstance().getConnection().Close();
                        reader.Close();
                        return lista;
                    }
                }
            }catch(Exception ex)
            {
                throw new Exception(ex.Message);
                return lista;
            }
        }
    }
}

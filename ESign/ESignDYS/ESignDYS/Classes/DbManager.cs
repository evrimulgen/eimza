using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.Sql;
using System.Data.SqlClient;
using System.Data.SqlTypes;

namespace ESignDYS.Classes
{
    public class DbManager
    {
        static SqlConnection connection = null;

        public static SqlConnection getConnection()
        {
            if (connection == null)
                connection = new SqlConnection(Properties.Settings.Default.DB_CONNECTION_STRING);
            return connection;
        }

        public static DataTable getDataTable(String sqlQuery)
        {
            DataTable dt = new DataTable();
            using (SqlDataAdapter da = new SqlDataAdapter(sqlQuery, getConnection()))
            {
                da.Fill(dt);
            }
            return dt;
        }

        public static int executeSql(String sqlString)
        {
            SqlCommand cmd = new SqlCommand(sqlString, getConnection());
            return cmd.ExecuteNonQuery();
        }

        public static SqlDataReader executeReader(String sqlString)
        {
            SqlCommand cmd = new SqlCommand(sqlString, getConnection());
            return cmd.ExecuteReader();
        }

        public static object executeScalar(String sqlString)
        {
            SqlCommand cmd = new SqlCommand(sqlString, getConnection());
            if (cmd.Connection.State != ConnectionState.Open)
            {
                cmd.Connection.Open();
            }
            return cmd.ExecuteScalar();
        }
    }
}
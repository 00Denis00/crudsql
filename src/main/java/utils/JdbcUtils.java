package utils;

import java.sql.*;

public class JdbcUtils
{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/db";

    static final String USER = "root";
    static final String PASSWORD = "den2701";

    private static Connection connection = null;

    private static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        if(connection == null)
        {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            return connection;
        }
        else
        {
            return connection;
        }
    }

    /*public static Statement getStatement()
    {
        try
        {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            return statement;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }*/

    public static PreparedStatement getPreparedStatement(String sql) throws Exception
    {
        return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    public static void closeConnection()
    {
        try {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

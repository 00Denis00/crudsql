package utils;

import java.sql.*;

public class JdbcUtils
{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/BD";

    static final String USER = "root";
    static final String PASSWORD = "den2701";

    private static Connection connection = null;
    private static Statement statement = null;

    public static Statement getStatement()
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
    }

    public static void closeConnection()
    {
        try {
            connection.close();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

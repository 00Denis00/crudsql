package repository;

import model.Tag;
import model.TagStatus;

import java.io.*;
import java.sql.*;
import java.util.*;

public interface TagRepository {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/BD";

    static final String USER = "root";
    static final String PASSWORD = "den2701";

    TagStatus deleted = TagStatus.DELETED;
    TagStatus active = TagStatus.ACTIVE;

    public default TagStatus check(Integer id)
    {
        //Проверяет элемент по ID
        try {
            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            boolean check = false;
            String sql;

            sql = "SELECT * FROM tag WHERE Id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            if(rs.next())
            {
                check = true;
            }
            else
            {
                check = false;
            }

            rs.close();
            connection.close();
            statement.close();

            if(check == false)
            {
                return deleted;
            }
            else
            {
                return active;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public default String getById(Integer id)
    {
        //Показывает элемент по ID
        try {
            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql;

            sql = "SELECT * FROM tag WHERE Id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            String str = "";
            while (rs.next()) {
                str = rs.getString("Name");
            }

            rs.close();
            connection.close();
            statement.close();

            return str;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public default List<String> getAll()
    {
        //Выводит все элементы файла
        try
        {
            List<String> res = new ArrayList<String>();

            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql;

            sql = "SELECT * FROM tag;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");

                String str = "Id: " + id + "     Name: " + name;
                res.add(str);
            }

            rs.close();
            connection.close();
            statement.close();

            return res;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public default void save(Tag tag) {
        //Создает элемент
        try {
            String name = tag.getName();
            int id = tag.getId();

            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql;

            sql = "insert into tag (Id, Name) values ('" + id + "', '" + name + "');";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public default void deleteById(Integer id) {
        //Удаляет элемент по ID
        try {
            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql;

            sql = "DELETE FROM tag WHERE Id = " + id + ";";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

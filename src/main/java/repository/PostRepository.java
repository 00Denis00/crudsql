package repository;

import model.Post;
import model.Tag;

import java.sql.*;
import java.util.*;

public interface PostRepository
{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/BD";

    static final String USER = "root";
    static final String PASSWORD = "den2701";

    public default List<String> getAll()
    {
        //Выводит все элементы файла
        try {
            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql;

            sql = "SELECT * FROM post;";
            ResultSet rs = statement.executeQuery(sql);

            List<Integer> list = new ArrayList<>();

            while (rs.next())
            {
                int x = rs.getInt("Id");
                list.add(x);
            }

            List<String> result = new ArrayList<>();

            for (int i = 0; i < list.size(); i++)
            {
                int id = list.get(i);
                sql = "SELECT * FROM post WHERE Id = " + id + ";";
                rs = statement.executeQuery(sql);

                String fname = "";
                String lname = "";

                while (rs.next()) {
                    fname = rs.getString("firstName");
                    lname = rs.getString("lastName");
                }

                sql = "SELECT * FROM ptags WHERE userId = " + id + ";";
                rs = statement.executeQuery(sql);

                StringBuilder builder = new StringBuilder();

                while (rs.next()) {
                    String name = rs.getString("Name");
                    int ide = rs.getInt("Id");
                    builder.append("(Id: ");
                    builder.append(ide);
                    builder.append(" ; Name: ");
                    builder.append(name);
                    builder.append(") ");
                }
                String res = builder.toString();
                String line = "Id: " + id + "\nFirst name: " + fname + "\nLast name: " + lname + "\nTags: " + res;
                result.add(line);
            }

            rs.close();
            connection.close();
            statement.close();

            return result;
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

            sql = "SELECT * FROM post WHERE Id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            String fname = "";
            String lname = "";

            while (rs.next())
            {
                fname = rs.getString("firstName");
                lname = rs.getString("lastName");
            }

            sql = "SELECT * FROM ptags WHERE userId = " + id + ";";
            rs = statement.executeQuery(sql);

            StringBuilder builder = new StringBuilder();

            while (rs.next())
            {
                String name = rs.getString("Name");
                int i = rs.getInt("Id");
                builder.append("(Id: ");
                builder.append(i);
                builder.append(" ; Name: ");
                builder.append(name);
                builder.append(") ");
            }
            String res = builder.toString();

            rs.close();
            connection.close();
            statement.close();

            String result = "First name: " + fname + "\nLast name: " + lname + "\nTags: " + res;
            return result;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public default void save(Post post)
    {
        //Создает элемент
        try {
            String firstName = post.getFirstName();
            String lastName = post.getLastName();
            int id = post.getId();
            List<Tag> tags = post.getTags();

            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql;

            sql = "insert into post (Id, firstName, lastName) values ('" + id + "', '" + firstName + "', '" + lastName + "');";
            statement.executeUpdate(sql);
            for(int i = 0; i < tags.size(); i++)
            {
                Tag tag = tags.get(i);
                int ide = tag.getId();
                String name = tag.getName();
                sql = "insert into ptags (Id, Name, userId) values ('" + ide + "', '" + name + "', '" + id + "');";
                statement.executeUpdate(sql);
            }

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public default void deleteById(Integer id)
    {
        //Удаляет элемент по ID
        try
        {
            Connection connection = null;
            Statement statement = null;

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String sql;

            sql = "DELETE FROM post WHERE Id = " + id + ";";
            statement.executeUpdate(sql);
            sql = "DELETE FROM ptags WHERE userId = " + id + ";";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

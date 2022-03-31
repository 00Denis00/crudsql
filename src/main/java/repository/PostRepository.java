package repository;

import model.Post;
import model.Tag;
import utils.JdbcUtils;

import java.sql.*;
import java.util.*;

public interface PostRepository extends GenericRepository <Post, Integer>
{
    public default List<Post> getAll()
    {
        //Выводит все элементы файла
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            sql = "SELECT * FROM post;";
            ResultSet rs = statement.executeQuery(sql);

            List<Integer> list = new ArrayList<>();

            while (rs.next())
            {
                int x = rs.getInt("Id");
                list.add(x);
            }

            List<Post> result = new ArrayList<>();

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

                List<Tag> tags = new ArrayList<>();

                while (rs.next())
                {
                    String name = rs.getString("Name");
                    int ide = rs.getInt("Id");

                    Tag tag = new Tag();

                    tag.setName(name);
                    tag.setId(ide);

                    tags.add(tag);
                }
                Post post = new Post();

                post.setId(id);
                post.setFirstName(fname);
                post.setLastName(lname);
                post.setTags(tags);

                result.add(post);
            }

            rs.close();

            return result;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public default Post getById(Integer id)
    {
        //Показывает элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            Post post = new Post();

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

            List<Tag> tags = new ArrayList<>();

            while (rs.next())
            {
                String name = rs.getString("Name");
                int i = rs.getInt("Id");

                Tag tag = new Tag();

                tag.setName(name);
                tag.setId(i);

                tags.add(tag);
            }

            post.setId(id);
            post.setFirstName(fname);
            post.setLastName(lname);
            post.setTags(tags);

            rs.close();

            return post;
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
        try (Statement statement = JdbcUtils.getStatement())
        {
            String firstName = post.getFirstName();
            String lastName = post.getLastName();
            int id = post.getId();
            List<Tag> tags = post.getTags();

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
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public default void deleteById(Integer id)
    {
        //Удаляет элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            sql = "DELETE FROM post WHERE Id = " + id + ";";
            statement.executeUpdate(sql);
            sql = "DELETE FROM ptags WHERE userId = " + id + ";";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

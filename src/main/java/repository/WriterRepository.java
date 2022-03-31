package repository;

import model.Post;
import model.Tag;
import model.Writer;
import utils.JdbcUtils;

import java.sql.*;
import java.util.*;

public interface WriterRepository extends GenericRepository <Writer, Integer>
{
    public default List<Writer> getAll()
    {
        //Выводит все элементы файла
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            List<Integer> ids = new ArrayList<>();
            List<Writer> writers = new ArrayList<>();

            sql = "SELECT * FROM writer;";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                int id = rs.getInt("Id");
                ids.add(id);
            }

            for (int z = 0; z < ids.size(); z++)
            {
                int id = ids.get(z);
                Writer writer = new Writer();

                sql = "SELECT * FROM writerPost WHERE writerId = " + id + ";";
                rs = statement.executeQuery(sql);
                List<Post> posts = new ArrayList<>();

                while(rs.next())
                {
                    int ide = rs.getInt("Id");
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");

                    Post post = new Post();
                    post.setId(ide);
                    post.setFirstName(fname);
                    post.setLastName(lname);
                    posts.add(post);
                }

                List<Post> result = new ArrayList<>();

                for(int i = 0; i < posts.size(); i++)
                {
                    Post post = posts.get(i);
                    int ide = post.getId();

                    sql = "SELECT * FROM postTag WHERE postId = " + ide + " AND writerId = " + id + ";";
                    rs = statement.executeQuery(sql);
                    List<Tag> tags = new ArrayList<>();

                    while (rs.next()) {
                        int idi = rs.getInt("Id");
                        String name = rs.getString("Name");

                        Tag tag = new Tag();

                        tag.setId(idi);
                        tag.setName(name);

                        tags.add(tag);
                    }

                    post.setTags(tags);
                    result.add(post);
                }

                sql = "SELECT * FROM writer WHERE Id = " + id + ";";
                rs = statement.executeQuery(sql);
                String writerName = "";
                while (rs.next())
                {
                    writerName = rs.getString("Name");
                }
                writer.setId(id);
                writer.setName(writerName);
                writer.setPosts(result);

                writers.add(writer);
            }

            rs.close();

            return writers;
        } catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public default void save(Writer writer)
    {
        //Создает элемент
        try (Statement statement = JdbcUtils.getStatement())
        {
            int id = writer.getId();
            String name = writer.getName();
            List<Post> posts = writer.getPosts();

            String sql;

            sql = "insert into writer (Id, Name) values ('" + id + "', '" + name + "');";
            statement.executeUpdate(sql);

            for(int i = 0; i < posts.size(); i++)
            {
                Post post = posts.get(i);
                int ide = post.getId();
                String fname = post.getFirstName();
                String lname = post.getLastName();
                List<Tag> tags = post.getTags();

                sql = "insert into writerPost (writerId, Id, firstName, lastName) values ('" + id + "', '" + ide + "', '" + fname + "', '" + lname + "');";
                statement.executeUpdate(sql);

                for(int j = 0; j < tags.size(); j++)
                {
                    Tag tag = tags.get(j);
                    int tagId = tag.getId();
                    String tagName = tag.getName();

                    sql = "insert into postTag (writerId, postId, Id, Name) values ('" + id + "', '" + ide + "', '" + tagId + "', '" + tagName + "');";
                    statement.executeUpdate(sql);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public default Writer getById(Integer id)
    {
        //Показывает элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            Writer writer = new Writer();

            String sql;

            sql = "SELECT * FROM writerPost WHERE writerId = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            List<Post> posts = new ArrayList<>();

            while(rs.next())
            {
                int ide = rs.getInt("Id");
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");

                Post post = new Post();
                post.setId(ide);
                post.setFirstName(fname);
                post.setLastName(lname);
                posts.add(post);
            }

            List<Post> result = new ArrayList<>();

            for(int i = 0; i < posts.size(); i++)
            {
                Post post = posts.get(i);
                int ide = post.getId();

                sql = "SELECT * FROM postTag WHERE postId = " + ide + " AND writerId = " + id + ";";
                rs = statement.executeQuery(sql);
                List<Tag> tags = new ArrayList<>();

                while (rs.next()) {
                    int idi = rs.getInt("Id");
                    String name = rs.getString("Name");

                    Tag tag = new Tag();

                    tag.setId(idi);
                    tag.setName(name);

                    tags.add(tag);
                }

                post.setTags(tags);
                result.add(post);
            }

            sql = "SELECT * FROM writer WHERE Id = " + id + ";";
            rs = statement.executeQuery(sql);
            String writerName = "";
            while (rs.next())
            {
                writerName = rs.getString("Name");
            }
            writer.setId(id);
            writer.setName(writerName);
            writer.setPosts(result);
            rs.close();

            return writer;
        } catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public default void deleteById(Integer id)
    {
        //Удаляет элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
           String sql;

            sql = "SELECT * FROM writerPost WHERE writerId = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            List<Integer> list = new ArrayList<>();

            while(rs.next())
            {
                int ide = rs.getInt("Id");
                list.add(ide);
            }

            for(int i = 0; i < list.size(); i++)
            {
                int ide = list.get(i);
                sql = "DELETE FROM postTag WHERE postId = " + ide + " AND writerId = " + id + ";";
                statement.executeUpdate(sql);
            }

            sql = "DELETE FROM writerPost WHERE writerId = " + id + ";";
            statement.executeUpdate(sql);

            sql = "DELETE FROM writer WHERE Id = " + id + ";";
            statement.executeUpdate(sql);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

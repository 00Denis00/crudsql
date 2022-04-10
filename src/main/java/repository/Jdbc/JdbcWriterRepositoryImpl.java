package repository.Jdbc;

import model.Post;
import model.Tag;
import model.Writer;
import utils.JdbcUtils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl
{
    public List<Writer> getAll()
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

                sql = "SELECT * FROM post WHERE writerId = " + id + ";";
                rs = statement.executeQuery(sql);

                List<Post> posts = new ArrayList<>();

                while(rs.next())
                {
                    int ide = rs.getInt("id");
                    String content = rs.getString("content");

                    Post post = new Post();
                    post.setId(ide);
                    post.setContent(content);
                    posts.add(post);
                }

                List<Post> result = new ArrayList<>();

                for(int i = 0; i < posts.size(); i++)
                {
                    Post post = posts.get(i);
                    int ide = post.getId();

                    sql = "SELECT * FROM tag WHERE postId = " + ide + ";";
                    rs = statement.executeQuery(sql);

                    List<Tag> tags = new ArrayList<>();

                    while (rs.next()) {
                        int idi = rs.getInt("id");
                        String name = rs.getString("name");

                        Tag tag = new Tag();

                        tag.setId(idi);
                        tag.setName(name);

                        tags.add(tag);
                    }

                    post.setTags(tags);
                    result.add(post);
                }

                sql = "SELECT * FROM writer WHERE id = " + id + ";";
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

    public void save(Writer writer)
    {
        //Создает элемент
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            String name = writer.getName();

            sql = "insert into writer (name) values ('" + name + "');";
            statement.executeUpdate(sql);

            System.out.println("Writer was saved");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public Writer getById(Integer id)
    {
        //Показывает элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            Writer writer = new Writer();

            String sql;

            boolean check = false;

            sql = "SELECT * FROM post WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                check = true;
            }

            if(check == true) {
                sql = "SELECT * FROM post WHERE writerId = " + id + ";";
                rs = statement.executeQuery(sql);

                List<Post> posts = new ArrayList<>();

                while (rs.next()) {
                    int ide = rs.getInt("id");
                    String content = rs.getString("content");

                    Post post = new Post();
                    post.setId(ide);
                    post.setContent(content);
                    posts.add(post);
                }

                List<Post> result = new ArrayList<>();

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    int ide = post.getId();

                    sql = "SELECT * FROM tag WHERE postId = " + ide + ";";
                    rs = statement.executeQuery(sql);

                    List<Tag> tags = new ArrayList<>();

                    while (rs.next()) {
                        int idi = rs.getInt("id");
                        String name = rs.getString("name");

                        Tag tag = new Tag();

                        tag.setId(idi);
                        tag.setName(name);

                        tags.add(tag);
                    }

                    post.setTags(tags);
                    result.add(post);
                }

                sql = "SELECT * FROM writer WHERE id = " + id + ";";
                rs = statement.executeQuery(sql);

                String writerName = "";
                while (rs.next()) {
                    writerName = rs.getString("Name");
                }

                writer.setId(id);
                writer.setName(writerName);
                writer.setPosts(result);

                rs.close();

                return writer;
            }
            else
            {
                System.out.println("There is no writer with that id!");
                rs.close();
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public void deleteById(Integer id)
    {
        //Удаляет элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            boolean check = false;

            sql = "SELECT * FROM writer WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                check = true;
            }

            if(check == true) {
                sql = "SELECT * FROM post WHERE writerId = " + id + ";";
                rs = statement.executeQuery(sql);
                List<Integer> list = new ArrayList<>();

                while (rs.next()) {
                    int ide = rs.getInt("Id");
                    list.add(ide);
                }

                for (int i = 0; i < list.size(); i++) {
                    int ide = list.get(i);
                    sql = "DELETE FROM tag WHERE postId = " + ide + ";";
                    statement.executeUpdate(sql);
                }

                sql = "DELETE FROM post WHERE writerId = " + id + ";";
                statement.executeUpdate(sql);

                sql = "DELETE FROM writer WHERE id = " + id + ";";
                statement.executeUpdate(sql);
            }
            else
            {
                System.out.println("There is no writer with that id!");
            }
            rs.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void update(Writer writer)
    {
        //Показывает элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            int id = writer.getId();
            String name = writer.getName();
            boolean check = false;

            String sql;

            sql = "SELECT * FROM writer WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
            {
                check = true;
            }

            if(check == true) {
                sql = "DELETE FROM writer WHERE id = " + id + ";";
                statement.executeUpdate(sql);

                sql = "insert into writer (id, name) values ('" + id + "', '" + name + "');";
                statement.executeUpdate(sql);

                System.out.println("Writer " + id + " was updated");
            }
            else
            {
                System.out.println("There is no writer with that id!");
                System.out.println("Writer wasn't updated");
            }
            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

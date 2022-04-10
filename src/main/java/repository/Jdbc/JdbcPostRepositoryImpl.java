package repository.Jdbc;

import model.Post;
import model.Tag;
import utils.JdbcUtils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl
{
    public List<Post> getAll()
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
                int id = rs.getInt("Id");
                list.add(id);
            }

            List<Post> result = new ArrayList<>();

            for (int i = 0; i < list.size(); i++)
            {
                int id = list.get(i);
                Post post = new Post();

                sql = "SELECT * FROM post WHERE Id = " + id + ";";
                rs = statement.executeQuery(sql);

                String content = "";

                while (rs.next())
                {
                    content = rs.getString("content");
                }

                sql = "SELECT * FROM tag WHERE postId = " + id + ";";
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

                post.setId(id);
                post.setContent(content);
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

    public Post getById(Integer id)
    {
        //Показывает элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            Post post = new Post();

            String sql;

            boolean check = false;

            sql = "SELECT * FROM post WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                check = true;
            }

            if(check == true) {
                sql = "SELECT * FROM post WHERE Id = " + id + ";";
                rs = statement.executeQuery(sql);

                String content = "";

                while (rs.next()) {
                    content = rs.getString("content");
                }

                sql = "SELECT * FROM tag WHERE postId = " + id + ";";
                rs = statement.executeQuery(sql);

                List<Tag> tags = new ArrayList<>();

                while (rs.next()) {
                    String name = rs.getString("Name");
                    int i = rs.getInt("Id");

                    Tag tag = new Tag();

                    tag.setName(name);
                    tag.setId(i);

                    tags.add(tag);
                }

                post.setId(id);
                post.setContent(content);
                post.setTags(tags);

                rs.close();

                return post;
            }
            else
            {
                System.out.println("There is no post with that id!");
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

    public void save(Post post)
    {
        //Создает элемент
        try (Statement statement = JdbcUtils.getStatement())
        {
            String content = post.getContent();
            int writerId = post.getWriterId();

            String sql;

            sql = "SELECT * FROM writer;";
            ResultSet rs = statement.executeQuery(sql);

            List<Integer> list = new ArrayList<>();
            while (rs.next())
            {
                int ide = rs.getInt("id");
                list.add(ide);
            }

            boolean coincidence = false;

            for(int i = 0; i < list.size(); i++)
            {
                if(writerId == list.get(i))
                {
                    coincidence = true;
                }
            }
            if(coincidence == true)
            {
                sql = "insert into post (content, writerId) values ('" + content + "', '" + writerId + "');";
                statement.executeUpdate(sql);
                System.out.println("Post was saved");
            }
            else
            {
                System.out.println("There is no writer with this id!");
                System.out.println("post wasn't saved");
            }
            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void deleteById(Integer id)
    {
        //Удаляет элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            boolean check = false;

            sql = "SELECT * FROM post WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                check = true;
            }

            if(check == true) {
                sql = "DELETE FROM post WHERE id = " + id + ";";
                statement.executeUpdate(sql);
                sql = "DELETE FROM tag WHERE postId = " + id + ";";
                statement.executeUpdate(sql);
            }
            else
            {
                System.out.println("There is no post with that id!");
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Post post)
    {
        try (Statement statement = JdbcUtils.getStatement())
        {
            int id = post.getId();
            String content = post.getContent();

            String sql;

            boolean check = false;
            int writerId = 0;

            sql = "SELECT * FROM post WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                check = true;
                writerId = rs.getInt("writerId");
            }

            if(check == true)
            {
                sql = "DELETE FROM post WHERE id = " + id + ";";
                statement.executeUpdate(sql);

                sql = "insert into post (id, content, writerId) values ('" + id + "', '" + content + "', '" + writerId + "');";
                statement.executeUpdate(sql);

                System.out.println("Post " + id + " was updated");
            }
            else
            {
                System.out.println("There is no post with that id!");
                System.out.println("Post wasn't updated");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

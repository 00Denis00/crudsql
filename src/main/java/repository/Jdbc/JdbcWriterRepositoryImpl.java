package repository.Jdbc;

import model.Post;
import model.Tag;
import model.Writer;
import repository.TagRepository;
import repository.WriterRepository;
import utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl
{
    public List<Writer> getAll()
    {
        //Выводит все элементы файла
        String sql = "SELECT * FROM writers;";

        List<Integer> ids = new ArrayList<>();
        List<Writer> writers = new ArrayList<>();
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                int id = rs.getInt("Id");
                ids.add(id);
            }

            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }

        for(int i = 0 ; i < ids.size() ; i++)
        {
            int id = ids.get(i);

            Writer writer = new Writer();
            String name = "";
            List<Post> posts = new ArrayList<>();
            Post post = new Post();
            post.setId(-100);
            List<Tag> tags = new ArrayList<>();
            sql = "SELECT * FROM writers, posts, post_tags, tags WHERE writers.id = " + id + " AND posts.writer_id = writers.id AND post_tags.post_id = posts.id AND tags.id = post_tags.tag_id ORDER BY posts.id;";
            try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
            {
                ResultSet rs = statement.executeQuery();
                while(rs.next())
                {
                    name = rs.getString("writers.name");

                    int postsLastId = post.getId();
                    int postId = rs.getInt("posts.id");

                    if(postsLastId == postId)
                    {
                        int tagId = rs.getInt("tags.id");
                        String tagName = rs.getString("tags.name");
                        Tag tag = new Tag();
                        tag.setId(tagId);
                        tag.setName(tagName);
                        tags.add(tag);
                    }
                    else if(postsLastId == -100)
                    {
                        String postContent = rs.getString("posts.content");
                        post.setId(postId);
                        post.setContent(postContent);

                        int tagId = rs.getInt("tags.id");
                        String tagName = rs.getString("tags.name");
                        Tag tag = new Tag();
                        tag.setId(tagId);
                        tag.setName(tagName);
                        tags.add(tag);
                    }
                    else
                    {
                        post.setTags(tags);
                        tags = new ArrayList<>();
                        posts.add(post);
                        post = new Post();

                        String postContent = rs.getString("posts.content");
                        post.setId(postId);
                        post.setContent(postContent);

                        int tagId = rs.getInt("tags.id");
                        String tagName = rs.getString("tags.name");
                        Tag tag = new Tag();
                        tag.setId(tagId);
                        tag.setName(tagName);
                        tags.add(tag);
                    }
                }
                post.setTags(tags);
                posts.add(post);

                rs.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            writer.setId(id);
            writer.setName(name);
            writer.setPosts(posts);

            writers.add(writer);
        }
        return writers;
    }

    public Writer save(Writer writer)
    {
        //Создает элемент
        String name = writer.getName();
        int id = 0;
        String sql = "insert into writers(name) values ('" + name + "');";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next())
            {
                id = rs.getInt(1);
            }

            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        writer.setId(id);
        return writer;
    }

    public Writer getById(Integer id)
    {
        Writer writer = new Writer();
        String name = "";
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        post.setId(-100);
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM writers, posts, post_tags, tags WHERE writers.id = " + id + " AND posts.writer_id = writers.id AND post_tags.post_id = posts.id AND tags.id = post_tags.tag_id ORDER BY posts.id;";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                name = rs.getString("writers.name");

                int postsLastId = post.getId();
                int postId = rs.getInt("posts.id");

                if(postsLastId == postId)
                {
                    int tagId = rs.getInt("tags.id");
                    String tagName = rs.getString("tags.name");
                    Tag tag = new Tag();
                    tag.setId(tagId);
                    tag.setName(tagName);
                    tags.add(tag);
                }
                else if(postsLastId == -100)
                {
                    String postContent = rs.getString("posts.content");
                    post.setId(postId);
                    post.setContent(postContent);

                    int tagId = rs.getInt("tags.id");
                    String tagName = rs.getString("tags.name");
                    Tag tag = new Tag();
                    tag.setId(tagId);
                    tag.setName(tagName);
                    tags.add(tag);
                }
                else
                {
                    post.setTags(tags);
                    tags = new ArrayList<>();
                    posts.add(post);
                    post = new Post();

                    String postContent = rs.getString("posts.content");
                    post.setId(postId);
                    post.setContent(postContent);

                    int tagId = rs.getInt("tags.id");
                    String tagName = rs.getString("tags.name");
                    Tag tag = new Tag();
                    tag.setId(tagId);
                    tag.setName(tagName);
                    tags.add(tag);
                }
            }
            post.setTags(tags);
            posts.add(post);

            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        writer.setId(id);
        writer.setName(name);
        writer.setPosts(posts);
        return writer;
    }

    public void deleteById(Integer id)
    {
        //Удаляет элемент по ID
        String sql = "DELETE FROM writers WHERE id = " + id + ";";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public Writer update(Writer writer)
    {
        //Показывает элемент по ID
        String name = writer.getName();
        int id = writer.getId();
        String sql = "UPDATE writers SET name='" + name + "' WHERE id=" + id + ";";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return writer;
    }
}

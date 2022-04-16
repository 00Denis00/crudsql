package repository.Jdbc;

import model.Post;
import model.Tag;
import repository.PostRepository;
import repository.TagRepository;
import utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository
{
    public List<Post> getAll()
    {
        //Выводит все элементы файла
        List<Integer> postIds = new ArrayList<>();
        String sql = "SELECT * FROM posts;";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("Id");
                postIds.add(id);
            }
            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }

        List<Post> posts = new ArrayList<>();

        for (int i = 0; i < postIds.size(); i++)
        {
            int id = postIds.get(i);
            String content = "";
            List<Tag> tags = new ArrayList<>();
            sql = "SELECT * FROM posts, post_tags, tags WHERE posts.id = " + id + " AND post_tags.post_id = " + id + " AND tags.id = post_tags.tag_id;";
            try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
            {
                ResultSet rs = statement.executeQuery();
                while(rs.next())
                {
                    content = rs.getString("content");

                    int tagId = rs.getInt("tag_id");
                    String tagName = rs.getString("name");

                    Tag tag = new Tag();
                    tag.setId(tagId);
                    tag.setName(tagName);

                    tags.add(tag);
                }
                rs.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            Post post = new Post();
            post.setId(id);
            post.setContent(content);
            post.setTags(tags);
            posts.add(post);
        }
        return posts;
    }

    public Post getById(Integer id)
    {
        //Показывает элемент по ID
        String content = "";
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM posts, post_tags, tags WHERE posts.id = " + id + " AND post_tags.post_id = " + id + " AND tags.id = post_tags.tag_id;";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                content = rs.getString("content");

                int tagId = rs.getInt("tag_id");
                String tagName = rs.getString("name");

                Tag tag = new Tag();
                tag.setId(tagId);
                tag.setName(tagName);

                tags.add(tag);
            }

            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.setTags(tags);
        return post;
    }

    public Post save(Post post) {
        //Создает элемент
        int id = 0;
        String content = post.getContent();
        int writerId = post.getWriterId();
        List<Tag> tags = post.getTags();
        String sql = "insert into posts(content, writer_id) values ('" + content + "', '" + writerId + "');";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql)) {
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        post.setId(id);

        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            int tagId = tag.getId();
            sql = "insert into post_tags(post_id, tag_id) values (" + id + ", " + tagId + ");";
            try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql)) {
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return post;
    }

    public void deleteById(Integer id)
    {
        //Удаляет элемент по ID
        String sql = "DELETE FROM posts WHERE id = " + id + ";";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public Post update(Post post)
    {
        String content = post.getContent();
        int id = post.getId();
        String sql = "UPDATE posts SET content='" + content + "' WHERE id=" + id + ";";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return post;
    }
}



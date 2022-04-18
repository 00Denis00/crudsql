package repository.Jdbc;

import model.Tag;
import model.TagStatus;
import repository.TagRepository;
import utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTagRepositoryImpl implements TagRepository
{

    public TagStatus check(Integer id)
    {
        TagStatus deleted = TagStatus.DELETED;
        TagStatus active = TagStatus.ACTIVE;

        boolean check = false;

        String sql = String.format("SELECT * FROM tags WHERE id = %d;", id);
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                check = true;
            }

            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        if(check == true)
        {
            return active;
        }
        else
        {
            return deleted;
        }
    }

    public Tag getById(Integer id)
    {
        String name = "";
        String sql = String.format("SELECT * FROM tags WHERE id = %d;", id);
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                name = rs.getString("name");
            }

            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        return tag;
    }

    public List<Tag> getAll()
    {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tags;";
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Tag tag = new Tag();

                tag.setId(id);
                tag.setName(name);

                tags.add(tag);
            }

            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return tags;
    }

    public Tag save(Tag tag)
    {
        String name = tag.getName();
        int id = 0;
        String sql = String.format("insert into tags(name) values ('%s');", name);
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
        tag.setId(id);
        return tag;
    }

    public Tag update(Tag tag)
    {
        String name = tag.getName();
        int id = tag.getId();
        String sql = String.format("UPDATE tags SET name='%s' WHERE id=%d;", name, id);
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return tag;
    }

    public void deleteById(Integer id)
    {
        String sql = String.format("DELETE FROM tags WHERE id = %d;", id);
        try (PreparedStatement statement = JdbcUtils.getPreparedStatement(sql))
        {
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

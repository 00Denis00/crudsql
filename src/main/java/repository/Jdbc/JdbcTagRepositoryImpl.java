package repository.Jdbc;

import model.Tag;
import model.TagStatus;
import repository.TagRepository;
import utils.JdbcUtils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTagRepositoryImpl implements TagRepository
{
    TagStatus deleted = TagStatus.DELETED;
    TagStatus active = TagStatus.ACTIVE;

    public TagStatus check(Integer id)
    {
        try (Statement statement = JdbcUtils.getStatement())
        {
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

    public Tag getById(Integer id)
    {
        try (Statement statement = JdbcUtils.getStatement())
        {
            Tag tag = new Tag();

            String sql;

            boolean check = false;

            sql = "SELECT * FROM tag WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                check = true;
            }

            if(check == true) {
                sql = "SELECT * FROM tag WHERE Id = " + id + ";";
                rs = statement.executeQuery(sql);

                String name = "";
                while (rs.next()) {
                    name = rs.getString("Name");
                }

                tag.setId(id);
                tag.setName(name);

                rs.close();

                return tag;
            }
            else
            {
                System.out.println("There is no post with that id!");
                rs.close();
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Tag> getAll()
    {
        try (Statement statement = JdbcUtils.getStatement())
        {
            List<Tag> tags = new ArrayList<>();

            String sql;

            sql = "SELECT * FROM tag;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Tag tag = new Tag();

                tag.setId(id);
                tag.setName(name);

                tags.add(tag);
            }

            rs.close();

            return tags;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public void save(Tag tag) {
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;
            sql = "SELECT * FROM post;";
            ResultSet rs = statement.executeQuery(sql);

            List<Integer> list = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                list.add(id);
            }

            String name = tag.getName();
            int postId = tag.getPostId();
            boolean coincidence = false;

            for(int i = 0; i < list.size(); i++)
            {
                if(postId == list.get(i))
                {
                    coincidence = true;
                }
            }
            if(coincidence == true)
            {
                sql = "insert into tag (name, postId) values ('" + name + "', '" + postId + "');";
                statement.executeUpdate(sql);
                System.out.println("Tag was saved");
            }
            else
            {
                System.out.println("There is no post with that id!");
                System.out.println("Tag wasn't saved");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Tag tag) {
        try (Statement statement = JdbcUtils.getStatement())
        {
            int id = tag.getId();
            String name = tag.getName();
            int postId = 0;

            String sql;
            sql = "SELECT * FROM tag WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            boolean check = false;
            while (rs.next())
            {
                check = true;
                postId = rs.getInt("postId");
            }

            if(check == true) {
                sql = "DELETE FROM tag WHERE Id = " + id + ";";
                statement.executeUpdate(sql);

                sql = "insert into tag (id, name, postId) values ('" + id + "', '" + name + "', '" + postId + "');";
                statement.executeUpdate(sql);

                System.out.println("Tag " + id + " was updated");
            }
            else
            {
                System.out.println("There is no tag with that id!");
                System.out.println("Tag wasn't updated");
            }
            rs.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteById(Integer id) {
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            boolean check = false;

            sql = "SELECT * FROM tag WHERE id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                check = true;
            }

            if(check == true) {
                sql = "DELETE FROM tag WHERE Id = " + id + ";";
                statement.executeUpdate(sql);
            }
            else
            {
                System.out.println("There is no tag with that id!");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

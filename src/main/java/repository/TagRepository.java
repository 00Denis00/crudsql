package repository;

import model.Tag;
import model.TagStatus;
import utils.JdbcUtils;

import java.io.*;
import java.sql.*;
import java.util.*;

public interface TagRepository extends GenericRepository <Tag, Integer>
{
    TagStatus deleted = TagStatus.DELETED;
    TagStatus active = TagStatus.ACTIVE;

    public default TagStatus check(Integer id)
    {
        //Проверяет элемент по ID
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

    public default Tag getById(Integer id)
    {
        //Показывает элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            Tag tag = new Tag();

            String sql;

            sql = "SELECT * FROM tag WHERE Id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            String name = "";
            while (rs.next()) {
                name = rs.getString("Name");
            }

            tag.setId(id);
            tag.setName(name);

            rs.close();

            return tag;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public default List<Tag> getAll()
    {
        //Выводит все элементы файла
        try (Statement statement = JdbcUtils.getStatement())
        {
            List<Tag> tags = new ArrayList<>();

            String sql;

            sql = "SELECT * FROM tag;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");

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

    public default void save(Tag tag) {
        //Создает элемент
        try (Statement statement = JdbcUtils.getStatement())
        {
            String name = tag.getName();
            int id = tag.getId();

            String sql;

            sql = "insert into tag (Id, Name) values ('" + id + "', '" + name + "');";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public default void deleteById(Integer id) {
        //Удаляет элемент по ID
        try (Statement statement = JdbcUtils.getStatement())
        {
            String sql;

            sql = "DELETE FROM tag WHERE Id = " + id + ";";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

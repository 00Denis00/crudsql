package repository;

import model.Tag;
import model.TagStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface GenericRepository <T, ID>
{
    T getById(ID id);

    List<T> getAll();

    void save(T t);

    void deleteById(ID id);
}

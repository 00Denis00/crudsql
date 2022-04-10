package controller;

import model.Writer;
import repository.Jdbc.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterController
{
    JdbcWriterRepositoryImpl writerRepository = new JdbcWriterRepositoryImpl(){};
    public void deleteById(Integer id)
    {
        writerRepository.deleteById(id);
    }
    public Writer getById(Integer id)
    {
        Writer writer = writerRepository.getById(id);
        return writer;
    }
    public List<Writer> getAll()
    {
        List<Writer> writers = writerRepository.getAll();
        return writers;
    }
    public void update(Writer writer)
    {
        writerRepository.update(writer);
    }
    public void save(Writer writer)
    {
        writerRepository.save(writer);
    }
}
